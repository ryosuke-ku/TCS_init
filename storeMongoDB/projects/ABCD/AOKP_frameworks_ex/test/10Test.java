    public void testAreAllItemsEnabledFalse() {
        TestCompositeCursorAdapter adapter = new TestCompositeCursorAdapter();
        adapter.addPartition(true, false);
        adapter.addPartition(true, true);

        assertFalse(adapter.areAllItemsEnabled());
    }
