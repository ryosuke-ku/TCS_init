  public OperationStatus getOperationStatus(OperationHandle opHandle)
      throws HiveSQLException {
    Operation operation = sessionManager.getOperationManager().getOperation(opHandle);
    /**
     * If this is a background operation run asynchronously,
     * we block for a duration determined by a step function, before we return
     * However, if the background operation is complete, we return immediately.
     */
    if (operation.shouldRunAsync()) {
      HiveConf conf = operation.getParentSession().getHiveConf();
      long maxTimeout = HiveConf.getTimeVar(conf,
          HiveConf.ConfVars.HIVE_SERVER2_LONG_POLLING_TIMEOUT, TimeUnit.MILLISECONDS);

      final long elapsed = System.currentTimeMillis() - operation.getBeginTime();
      // A step function to increase the polling timeout by 500 ms every 10 sec, 
      // starting from 500 ms up to HIVE_SERVER2_LONG_POLLING_TIMEOUT
      final long timeout = Math.min(maxTimeout, (elapsed / TimeUnit.SECONDS.toMillis(10) + 1) * 500);

      try {
        operation.getBackgroundHandle().get(timeout, TimeUnit.MILLISECONDS);
      } catch (TimeoutException e) {
        // No Op, return to the caller since long polling timeout has expired
        LOG.trace(opHandle + ": Long polling timed out");
      } catch (CancellationException e) {
        // The background operation thread was cancelled
        LOG.trace(opHandle + ": The background operation was cancelled", e);
      } catch (ExecutionException e) {
        // The background operation thread was aborted
        LOG.warn(opHandle + ": The background operation was aborted", e);
      } catch (InterruptedException e) {
        // No op, this thread was interrupted
        // In this case, the call might return sooner than long polling timeout
      }
    }
    OperationStatus opStatus = operation.getStatus();
    LOG.debug(opHandle + ": getOperationStatus()");
    return opStatus;
  }
