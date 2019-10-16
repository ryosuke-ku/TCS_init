    public void testMarkDirtyAndChanged_onlyUpdatesChanged() {
        TransactionContext context = new TransactionContext(false);

        context.markRawContactDirtyAndChanged(1L, true /* isSyncAdapter */);

        assertEquals(1, context.getChangedRawContactIds().size());
        assertEquals(0, context.getDirtyRawContactIds().size());
    }
