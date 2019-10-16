    public void testGetOffsetForPosition() {
        TestCompositeCursorAdapter adapter = new TestCompositeCursorAdapter();
        adapter.addPartition(true, false);
        adapter.addPartition(true, true);

        adapter.changeCursor(0, makeCursor("a", 1));
        adapter.changeCursor(1, makeCursor("b", 2));

        assertEquals(0, adapter.getOffsetInPartition(0));
        assertEquals(-1, adapter.getOffsetInPartition(1));
        assertEquals(0, adapter.getOffsetInPartition(2));
        assertEquals(1, adapter.getOffsetInPartition(3));
    }
