    public void addEntry(final BookieSocketAddress addr, final long ledgerId, final byte[] masterKey,
            final long entryId,
            final ChannelBuffer toSend, final WriteCallback cb, final Object ctx, final int options) {
        closeLock.readLock().lock();
        try {
            final PerChannelBookieClientPool client = lookupClient(addr, entryId);
            if (client == null) {
                cb.writeComplete(getRc(BKException.Code.BookieHandleNotAvailableException),
                                 ledgerId, entryId, addr, ctx);
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
                                    cb.writeComplete(rc, ledgerId, entryId, addr, ctx);
                                }
                            });
                        } catch (RejectedExecutionException re) {
                            cb.writeComplete(getRc(BKException.Code.InterruptedException),
                                    ledgerId, entryId, addr, ctx);
                        }
                        return;
                    }
                    pcbc.addEntry(ledgerId, masterKey, entryId, toSend, cb, ctx, options);
                }
            });
        } finally {
            closeLock.readLock().unlock();
        }
    }
