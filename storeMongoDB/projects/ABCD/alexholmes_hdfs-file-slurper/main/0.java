  protected void doWork() throws InterruptedException {
    try {
      copyFile(fileSystemManager.pollForInboundFile(pollSleepUnit, config.getPollSleepPeriodMillis()));
    } catch (InterruptedException ie) {
      throw ie;
    } catch (Throwable t) {
      log.warn("Caught exception in doWork", t);
    }
  }
