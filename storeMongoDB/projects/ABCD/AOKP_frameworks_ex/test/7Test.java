    public void testGetPositionForPartition() {
        TestCompositeCursorAdapter adapter = new TestCompositeCursorAdapter();
        adapter.addPartition(true, true);
        adapter.addPartition(true, true);

        adapter.changeCursor(0, makeCursor("a", 1));
        adapter.changeCursor(1, makeCursor("b", 2));

        assertEquals(0, adapter.getPositionForPartition(0));
        assertEquals(2, adapter.getPositionForPartition(1));
    }
