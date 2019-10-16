    public void setTransactionSuccessful() {
        verifyDbIsOpen();
        if (!mLock.isHeldByCurrentThread()) {
            throw new IllegalStateException("no transaction pending");
        }
        if (mInnerTransactionIsSuccessful) {
            throw new IllegalStateException(
                    "setTransactionSuccessful may only be called once per call to beginTransaction");
        }
        mInnerTransactionIsSuccessful = true;
    }
