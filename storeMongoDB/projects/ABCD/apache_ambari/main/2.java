  public long getPendingCount() {
    long pendingCount = 0;
    for (SolrWorkerThread solrWorkerThread : workerThreadList) {
      pendingCount += solrWorkerThread.localBuffer.size();
    }
    return pendingCount;
  }
