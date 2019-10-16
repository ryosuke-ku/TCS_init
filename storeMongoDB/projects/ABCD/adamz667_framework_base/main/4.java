    private void beginTransaction(SQLiteTransactionListener transactionListener,
            boolean exclusive) {
        verifyDbIsOpen();
        lockForced(BEGIN_SQL);
        boolean ok = false;
        try {
            // If this thread already had the lock then get out
            if (mLock.getHoldCount() > 1) {
                if (mInnerTransactionIsSuccessful) {
                    String msg = "Cannot call beginTransaction between "
                            + "calling setTransactionSuccessful and endTransaction";
                    IllegalStateException e = new IllegalStateException(msg);
                    Log.e(TAG, "beginTransaction() failed", e);
                    throw e;
                }
                ok = true;
                return;
            }

            // This thread didn't already have the lock, so begin a database
            // transaction now.
            if (exclusive && mConnectionPool == null) {
                execSQL("BEGIN EXCLUSIVE;");
            } else {
                execSQL("BEGIN IMMEDIATE;");
            }
            mTransStartTime = SystemClock.uptimeMillis();
            mTransactionListener = transactionListener;
            mTransactionIsSuccessful = true;
            mInnerTransactionIsSuccessful = false;
            if (transactionListener != null) {
                try {
                    transactionListener.onBegin();
                } catch (RuntimeException e) {
                    execSQL("ROLLBACK;");
                    throw e;
                }
            }
            ok = true;
        } finally {
            if (!ok) {
                // beginTransaction is called before the try block so we must release the lock in
                // the case of failure.
                unlockForced();
            }
        }
    }
