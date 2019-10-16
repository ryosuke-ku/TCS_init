    public void testGetItemId() {
        CompositeListAdapter adapter = new CompositeListAdapter();
        adapter.addAdapter(mAdapter1);
        adapter.addAdapter(mAdapter2);
        adapter.addAdapter(mAdapter3);

        assertEquals(0, adapter.getItemId(0));
        assertEquals(1, adapter.getItemId(1));
        assertEquals(0, adapter.getItemId(2));
        assertEquals(1, adapter.getItemId(3));
        assertEquals(2, adapter.getItemId(4));
    }
