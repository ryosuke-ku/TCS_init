    public boolean enableWriteAheadLogging() {
        // make sure the database is not READONLY. WAL doesn't make sense for readonly-databases.
        if (isReadOnly()) {
            return false;
        }
        // acquire lock - no that no other thread is enabling WAL at the same time
        lock();
        try {
            if (mConnectionPool != null) {
                // already enabled
                return true;
            }
            if (mPath.equalsIgnoreCase(MEMORY_DB_PATH)) {
                Log.i(TAG, "can't enable WAL for memory databases.");
                return false;
            }

            // make sure this database has NO attached databases because sqlite's write-ahead-logging
            // doesn't work for databases with attached databases
            if (mHasAttachedDbs) {
                if (Log.isLoggable(TAG, Log.DEBUG)) {
                    Log.d(TAG,
                            "this database: " + mPath + " has attached databases. can't  enable WAL.");
                }
                return false;
            }
            mConnectionPool = new DatabaseConnectionPool(this);
            setJournalMode(mPath, "WAL");
            return true;
        } finally {
            unlock();
        }
    }
