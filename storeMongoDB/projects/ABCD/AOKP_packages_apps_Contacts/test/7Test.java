    public void testAreAllItemsEnabledWithInvalidation() {
        CompositeListAdapter adapter = new CompositeListAdapter();
        adapter.addAdapter(mAdapter1);
        assertTrue(adapter.areAllItemsEnabled());

        mAdapter3.allItemsEnabled = false;
        adapter.addAdapter(mAdapter3);

        assertFalse(adapter.areAllItemsEnabled());
    }
