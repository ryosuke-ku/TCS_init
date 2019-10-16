  public TabletLocation locateTablet(ClientContext context, Text row, boolean skipRow, boolean retry) throws AccumuloException, AccumuloSecurityException,
      TableNotFoundException {

    OpTimer timer = null;

    if (log.isTraceEnabled()) {
      log.trace("tid={} Locating tablet  table={} row={} skipRow={} retry={}", Thread.currentThread().getId(), tableId, TextUtil.truncate(row), skipRow, retry);
      timer = new OpTimer().start();
    }

    while (true) {

      LockCheckerSession lcSession = new LockCheckerSession();
      TabletLocation tl = _locateTablet(context, row, skipRow, retry, true, lcSession);

      if (retry && tl == null) {
        sleepUninterruptibly(100, TimeUnit.MILLISECONDS);
        if (log.isTraceEnabled())
          log.trace("Failed to locate tablet containing row {} in table {}, will retry...", TextUtil.truncate(row), tableId);
        continue;
      }

      if (timer != null) {
        timer.stop();
        log.trace("tid={} Located tablet {} at {} in {}", Thread.currentThread().getId(), (tl == null ? "null" : tl.tablet_extent), (tl == null ? "null"
            : tl.tablet_location), String.format("%.3f secs", timer.scale(TimeUnit.SECONDS)));
      }

      return tl;
    }
  }
