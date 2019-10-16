            public void readComplete(int rc, LedgerHandle lh, Enumeration<LedgerEntry> seq, Object ctx) {
                returnCode.set(rc);
                receivedResponses.incrementAndGet();
                counter.countDown();
            }
