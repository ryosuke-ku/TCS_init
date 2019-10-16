    public void testClearExceptSearchIndexUpdates_returnsNewSets() {
        TransactionContext context = new TransactionContext(false);
        context.markRawContactDirtyAndChanged(1L, false);
        context.rawContactUpdated(1L);
        context.rawContactInserted(1L, 1L);
        context.syncStateUpdated(1L, new Object());

        context.clearExceptSearchIndexUpdates();

        Set<Long> newDirty = context.getDirtyRawContactIds();
        Set<Long> newChanged = context.getChangedRawContactIds();
        Set<Long> newInserted = context.getInsertedRawContactIds();
        Set<Long> newUpdated = context.getUpdatedRawContactIds();
        Set<Map.Entry<Long, Object>> newSync = context.getUpdatedSyncStates();

        assertTrue(newDirty.isEmpty());
        assertTrue(newChanged.isEmpty());
        assertTrue(newInserted.isEmpty());
        assertTrue(newUpdated.isEmpty());
        assertTrue(newSync.isEmpty());
    }
