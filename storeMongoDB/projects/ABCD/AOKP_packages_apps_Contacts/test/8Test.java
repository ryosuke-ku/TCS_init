    public void testIsEnabledWhenAllEnabledAtLeastOneAdapter() {
        mAdapter1.allItemsEnabled = false;
        mAdapter1.enabledItems.add(1);
        mAdapter3.allItemsEnabled = false;
        mAdapter3.enabledItems.add(1);

        CompositeListAdapter adapter = new CompositeListAdapter();
        adapter.addAdapter(mAdapter1);
        adapter.addAdapter(mAdapter3);

        assertFalse(adapter.isEnabled(0));
        assertTrue(adapter.isEnabled(1));
        assertFalse(adapter.isEnabled(2));
        assertTrue(adapter.isEnabled(3));
        assertFalse(adapter.isEnabled(4));
    }
