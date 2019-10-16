    public void testIsNotEmptyWithNonEmptyCollection() {
        final Collection<String> coll = new ArrayList<String>();
        coll.add("item");
        assertEquals(true, CollectionUtils.isNotEmpty(coll));
    }
