    public void close() throws InterruptedException, BKException {
        closeLock.writeLock().lock();
        try {
            if (closed) {
                return;
            }
            closed = true;
        } finally {
            closeLock.writeLock().unlock();
        }

        // Close bookie client so all pending bookie requests would be failed
        // which will reject any incoming bookie requests.
        bookieClient.close();
        try {
            // Close ledger manage so all pending metadata requests would be failed
            // which will reject any incoming metadata requests.
            ledgerManager.close();
            ledgerIdGenerator.close();
            ledgerManagerFactory.uninitialize();
        } catch (IOException ie) {
            LOG.error("Failed to close ledger manager : ", ie);
        }

        // Close the scheduler
        scheduler.shutdown();
        if (!scheduler.awaitTermination(10, TimeUnit.SECONDS)) {
            LOG.warn("The scheduler did not shutdown cleanly");
        }
        mainWorkerPool.shutdown();
        if (!mainWorkerPool.awaitTermination(10, TimeUnit.SECONDS)) {
            LOG.warn("The mainWorkerPool did not shutdown cleanly");
        }

        if (ownTimer) {
            requestTimer.stop();
        }
        if (ownChannelFactory) {
            channelFactory.releaseExternalResources();
        }
        if (ownZKHandle) {
            zk.close();
        }
    }
