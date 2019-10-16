    public void testGetPartitionForPosition() {
        TestCompositeCursorAdapter adapter = new TestCompositeCursorAdapter();
        adapter.addPartition(true, false);
        adapter.addPartition(true, true);

        adapter.changeCursor(0, makeCursor("a", 1));
        adapter.changeCursor(1, makeCursor("b", 2));

        assertEquals(0, adapter.getPartitionForPosition(0));
        assertEquals(1, adapter.getPartitionForPosition(1));
        assertEquals(1, adapter.getPartitionForPosition(2));
        assertEquals(1, adapter.getPartitionForPosition(3));
    }
