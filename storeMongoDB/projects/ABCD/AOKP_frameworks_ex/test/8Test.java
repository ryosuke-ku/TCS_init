    public void testGetViewWithHeadersNoEmptySections() {
        TestCompositeCursorAdapter adapter = new TestCompositeCursorAdapter();
        adapter.addPartition(false, true);
        adapter.addPartition(false, true);

        adapter.changeCursor(0, makeCursor("a", 1));
        adapter.changeCursor(1, makeCursor("b", 2));

        for (int i = 0; i < adapter.getCount(); i++) {
            adapter.getView(i, null, null);
        }

        assertEquals("0a[H] 0a[0] 1b[H] 1b[0] 1b[1]", adapter.toString());
    }
