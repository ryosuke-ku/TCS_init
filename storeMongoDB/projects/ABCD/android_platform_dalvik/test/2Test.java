    public void test_empty() {
        ListIntSet set = new ListIntSet();

        IntIterator iter = set.iterator();

        assertFalse(iter.hasNext());
    }
