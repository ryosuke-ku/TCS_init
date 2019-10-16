    public boolean isDbLockedByCurrentThread() {
        return mLock.isHeldByCurrentThread();
    }
