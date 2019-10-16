    public ContentProviderResult[] applyBatch(ArrayList<ContentProviderOperation> operations)
            throws OperationApplicationException {
        waitForAccess(mWriteAccessLatch);
        return super.applyBatch(operations);
    }
