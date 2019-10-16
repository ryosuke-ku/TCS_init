    public void endTransaction() {
        verifyLockOwner();
        try {
            if (mInnerTransactionIsSuccessful) {
                mInnerTransactionIsSuccessful = false;
            } else {
                mTransactionIsSuccessful = false;
            }
            if (mLock.getHoldCount() != 1) {
                return;
            }
            RuntimeException savedException = null;
            if (mTransactionListener != null) {
                try {
                    if (mTransactionIsSuccessful) {
                        mTransactionListener.onCommit();
                    } else {
                        mTransactionListener.onRollback();
                    }
                } catch (RuntimeException e) {
                    savedException = e;
                    mTransactionIsSuccessful = false;
                }
            }
            if (mTransactionIsSuccessful) {
                execSQL(COMMIT_SQL);
                // if write-ahead logging is used, we have to take care of checkpoint.
                // TODO: should applications be given the flexibility of choosing when to
                // trigger checkpoint?
                // for now, do checkpoint after every COMMIT because that is the fastest
                // way to guarantee that readers will see latest data.
                // but this is the slowest way to run sqlite with in write-ahead logging mode.
                if (this.mConnectionPool != null) {
                    execSQL("PRAGMA wal_checkpoint;");
                    if (SQLiteDebug.DEBUG_SQL_STATEMENTS) {
                        Log.i(TAG, "PRAGMA wal_Checkpoint done");
                    }
                }
                // log the transaction time to the Eventlog.
                if (ENABLE_DB_SAMPLE) {
                    logTimeStat(getLastSqlStatement(), mTransStartTime, COMMIT_SQL);
                }
            } else {
                try {
                    execSQL("ROLLBACK;");
                    if (savedException != null) {
                        throw savedException;
                    }
                } catch (SQLException e) {
                    if (false) {
                        Log.d(TAG, "exception during rollback, maybe the DB previously "
                                + "performed an auto-rollback");
                    }
                }
            }
        } finally {
            mTransactionListener = null;
            unlockForced();
            if (false) {
                Log.v(TAG, "unlocked " + Thread.currentThread()
                        + ", holdCount is " + mLock.getHoldCount());
            }
        }
    }
