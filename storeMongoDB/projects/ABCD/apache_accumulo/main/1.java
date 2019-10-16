  public void invalidateCache() {
    int invalidatedCount;
    wLock.lock();
    try {
      invalidatedCount = metaCache.size();
      metaCache.clear();
    } finally {
      wLock.unlock();
    }
    if (log.isTraceEnabled())
      log.trace("invalidated all {} cache entries for table={}", invalidatedCount, tableId);
  }
