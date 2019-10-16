    /* package */ SQLiteDatabase getDbConnection(String sql) {
        verifyDbIsOpen();
        // this method should always be called with main database connection handle.
        // the only time when it is called with pooled database connection handle is
        // corruption occurs while trying to open a pooled database connection handle.
        // in that case, simply return 'this' handle
        if (isPooledConnection()) {
            return this;
        }

        // use the current connection handle if
        // 1. if the caller is part of the ongoing transaction, if any
        // 2. OR, if there is NO connection handle pool setup
        if (amIInTransaction() || mConnectionPool == null) {
            return this;
        } else {
            // get a connection handle from the pool
            if (Log.isLoggable(TAG, Log.DEBUG)) {
                assert mConnectionPool != null;
                Log.i(TAG, mConnectionPool.toString());
            }
            return mConnectionPool.get(sql);
        }
    }
