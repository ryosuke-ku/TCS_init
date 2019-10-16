    public boolean inTransaction() {
        return mLock.getHoldCount() > 0 || mTransactionUsingExecSql;
    }
