    public void readEntry(final BookieSocketAddress addr, final long ledgerId, final long entryId,
                          final ReadEntryCallback cb, final Object ctx) {
        closeLock.readLock().lock();
        try {
            final PerChannelBookieClientPool client = lookupClient(addr, entryId);
            if (client == null) {
                cb.readEntryComplete(getRc(BKException.Code.BookieHandleNotAvailableException),
                                     ledgerId, entryId, null, ctx);
                return;
            }

            client.obtain(new GenericCallback<PerChannelBookieClient>() {
                @Override
                public void operationComplete(final int rc, PerChannelBookieClient pcbc) {
                    if (rc != BKException.Code.OK) {
                        try {
                            executor.submitOrdered(ledgerId, new SafeRunnable() {
                                @Override
                                public void safeRun() {
                                    cb.readEntryComplete(rc, ledgerId, entryId, null, ctx);
                                }
                            });
                        } catch (RejectedExecutionException re) {
                            cb.readEntryComplete(getRc(BKException.Code.InterruptedException),
                                    ledgerId, entryId, null, ctx);
                        }
                        return;
                    }
                    pcbc.readEntry(ledgerId, entryId, cb, ctx);
                }
            });
        } finally {
            closeLock.readLock().unlock();
        }
    }
