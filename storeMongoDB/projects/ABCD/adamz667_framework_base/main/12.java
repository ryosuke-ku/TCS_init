    public boolean isDbLockedByOtherThreads() {
        return !mLock.isHeldByCurrentThread() && mLock.isLocked();
    }
