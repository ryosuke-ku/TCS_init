    public void close() {
        if (!isOpen()) {
            return;
        }
        if (Log.isLoggable(TAG, Log.DEBUG)) {
            Log.i(TAG, "closing db: " + mPath + " (connection # " + mConnectionNum);
        }
        lock();
        try {
            // some other thread could have closed this database while I was waiting for lock.
            // check the database state
            if (!isOpen()) {
                return;
            }
            closeClosable();
            // finalize ALL statements queued up so far
            closePendingStatements();
            releaseCustomFunctions();
            // close this database instance - regardless of its reference count value
            closeDatabase();
            if (mConnectionPool != null) {
                if (Log.isLoggable(TAG, Log.DEBUG)) {
                    assert mConnectionPool != null;
                    Log.i(TAG, mConnectionPool.toString());
                }
                mConnectionPool.close();
            }
        } finally {
            unlock();
        }
    }
