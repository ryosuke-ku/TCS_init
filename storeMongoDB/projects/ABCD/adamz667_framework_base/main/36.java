    public void disableWriteAheadLogging() {
        // grab database lock so that writeAheadLogging is not disabled from 2 different threads
        // at the same time
        lock();
        try {
            if (mConnectionPool == null) {
                return; // already disabled
            }
            mConnectionPool.close();
            setJournalMode(mPath, "TRUNCATE");
            mConnectionPool = null;
        } finally {
            unlock();
        }
    }
