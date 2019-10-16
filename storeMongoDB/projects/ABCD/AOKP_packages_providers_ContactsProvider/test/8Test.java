    public void testRawContactInserted_affectsChangedContacts() {
        TransactionContext context = new TransactionContext(false);
        assertTrue(context.getChangedRawContactIds().isEmpty());

        context.rawContactInserted(1L, 2L);
        assertEquals(1, context.getChangedRawContactIds().size());
        assertTrue(context.getChangedRawContactIds().contains(1L));

        context.rawContactInserted(5L, 10L);
        assertEquals(2, context.getChangedRawContactIds().size());
        assertTrue(context.getChangedRawContactIds().contains(5L));
    }
