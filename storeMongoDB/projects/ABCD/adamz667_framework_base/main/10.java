    /* package */ synchronized boolean amIInTransaction() {
        // always do this test on the main database connection - NOT on pooled database connection
        // since transactions always occur on the main database connections only.
        SQLiteDatabase db = (isPooledConnection()) ? mParentConnObj : this;
        boolean b = (!db.inTransaction()) ? false :
                db.mTransactionUsingExecSql || db.mLock.isHeldByCurrentThread();
        if (Log.isLoggable(TAG, Log.DEBUG)) {
            Log.i(TAG, "amIinTransaction: " + b);
        }
        return b;
    }
