    public void testNotifyDataSetInvalidated() {
        CompositeListAdapter adapter = new CompositeListAdapter();
        adapter.addAdapter(mAdapter1);

        TestDataSetObserver observer = new TestDataSetObserver();
        adapter.registerDataSetObserver(observer);

        mAdapter1.remove("A");
        assertEquals(1, observer.changeCount);
        assertEquals(0, observer.invalidationCount);
        assertEquals(1, adapter.getCount());

        mAdapter1.remove("B");
        assertEquals(1, observer.changeCount);
        assertEquals(1, observer.invalidationCount);
        assertEquals(0, adapter.getCount());
    }
