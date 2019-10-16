    public void testGetItemViewType() {
        mAdapter1.viewTypeCount = 2;
        mAdapter1.viewTypes.put(0, 1);
        mAdapter1.viewTypes.put(1, 0);

        mAdapter3.viewTypeCount = 3;
        mAdapter3.viewTypes.put(0, 1);
        mAdapter3.viewTypes.put(1, 2);
        mAdapter3.viewTypes.put(2, 0);

        CompositeListAdapter adapter = new CompositeListAdapter();
        adapter.addAdapter(mAdapter1);
        adapter.addAdapter(mAdapter2);
        adapter.addAdapter(mAdapter3);

        assertEquals(1, adapter.getItemViewType(0));
        assertEquals(0, adapter.getItemViewType(1));

        // Note: mAdapter2 throws in a +1

        assertEquals(4, adapter.getItemViewType(2));
        assertEquals(5, adapter.getItemViewType(3));
        assertEquals(3, adapter.getItemViewType(4));
    }
