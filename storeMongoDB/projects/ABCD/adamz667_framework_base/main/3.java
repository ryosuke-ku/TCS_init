    /* package */ void unlock() {
        if (!mLockingEnabled) return;
        if (SQLiteDebug.DEBUG_LOCK_TIME_TRACKING) {
            if (mLock.getHoldCount() == 1) {
                checkLockHoldTime();
            }
        }
        mLock.unlock();
    }
