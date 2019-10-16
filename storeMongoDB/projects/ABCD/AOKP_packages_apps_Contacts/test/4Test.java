    public void testGetViewTypeCount() {
        mAdapter1.viewTypeCount = 2;
        mAdapter2.viewTypeCount = 3;
        CompositeListAdapter adapter = new CompositeListAdapter();
        adapter.addAdapter(mAdapter1);
        adapter.addAdapter(mAdapter2);
        adapter.addAdapter(mAdapter3);

        // Note that mAdapter2 adds an implicit +1
        assertEquals(6, adapter.getViewTypeCount());
    }
