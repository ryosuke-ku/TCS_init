    private void lock(String sql, boolean forced) {
        // make sure this method is NOT being called from a 'synchronized' method
        if (Thread.holdsLock(this)) {
            Log.w(TAG, "don't lock() while in a synchronized method");
        }
        verifyDbIsOpen();
        if (!forced && !mLockingEnabled) return;
        boolean done = false;
        long timeStart = SystemClock.uptimeMillis();
        while (!done) {
            try {
                // wait for 30sec to acquire the lock
                done = mLock.tryLock(LOCK_WAIT_PERIOD, TimeUnit.SECONDS);
                if (!done) {
                    // lock not acquired in NSec. print a message and stacktrace saying the lock
                    // has not been available for 30sec.
                    Log.w(TAG, "database lock has not been available for " + LOCK_WAIT_PERIOD +
                            " sec. Current Owner of the lock is " + mLock.getOwnerDescription() +
                            ". Continuing to wait in thread: " + Thread.currentThread().getId());
                }
            } catch (InterruptedException e) {
                // ignore the interruption
            }
        }
        if (SQLiteDebug.DEBUG_LOCK_TIME_TRACKING) {
            if (mLock.getHoldCount() == 1) {
                // Use elapsed real-time since the CPU may sleep when waiting for IO
                mLockAcquiredWallTime = SystemClock.elapsedRealtime();
                mLockAcquiredThreadTime = Debug.threadCpuTimeNanos();
            }
        }
        if (sql != null) {
            if (ENABLE_DB_SAMPLE)  {
                logTimeStat(sql, timeStart, GET_LOCK_LOG_PREFIX);
            }
        }
    }
