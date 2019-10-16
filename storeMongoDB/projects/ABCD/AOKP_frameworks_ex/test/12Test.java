    public void testIsEnabled() {
        TestCompositeCursorAdapter adapter = new TestCompositeCursorAdapter();
        adapter.addPartition(true, false);
        adapter.addPartition(true, true);

        adapter.changeCursor(0, makeCursor("a", 1));
        adapter.changeCursor(1, makeCursor("b", 2));

        assertTrue(adapter.isEnabled(0));
        assertFalse(adapter.isEnabled(1));
        assertTrue(adapter.isEnabled(2));
        assertTrue(adapter.isEnabled(3));
    }
