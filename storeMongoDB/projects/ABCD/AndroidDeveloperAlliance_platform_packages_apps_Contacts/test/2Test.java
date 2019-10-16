    public void testNotifyDataSetChangedPropagated() {
        CompositeListAdapter adapter = new CompositeListAdapter();
        adapter.addAdapter(mAdapter1);
        adapter.addAdapter(mAdapter2);

        TestDataSetObserver observer = new TestDataSetObserver();
        adapter.registerDataSetObserver(observer);
        mAdapter1.add("X");

        assertEquals(1, observer.changeCount);
        assertEquals(0, observer.invalidationCount);
        assertEquals(3, adapter.getCount());
        assertEquals("A", adapter.getItem(0));
        assertEquals("B", adapter.getItem(1));
        assertEquals("X", adapter.getItem(2));

        mAdapter2.add("Y");
        assertEquals(2, observer.changeCount);
        assertEquals(0, observer.invalidationCount);
        assertEquals(4, adapter.getCount());
        assertEquals("A", adapter.getItem(0));
        assertEquals("B", adapter.getItem(1));
        assertEquals("X", adapter.getItem(2));
        assertEquals("Y", adapter.getItem(3));

    }
