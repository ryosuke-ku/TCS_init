    public void testGetViewWithShownEmptySections() {
        TestCompositeCursorAdapter adapter = new TestCompositeCursorAdapter();
        adapter.addPartition(true, true);
        adapter.addPartition(true, true);

        adapter.changeCursor(1, makeCursor("b", 2));

        for (int i = 0; i < adapter.getCount(); i++) {
            adapter.getView(i, null, null);
        }

        assertEquals("0[H] 1b[H] 1b[0] 1b[1]", adapter.toString());
    }
