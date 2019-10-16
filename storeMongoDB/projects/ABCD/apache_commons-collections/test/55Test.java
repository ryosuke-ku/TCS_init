    public void emptyIfNull() {
        assertTrue(CollectionUtils.emptyIfNull(null).isEmpty());
        final Collection<Object> collection = new ArrayList<Object>();
        assertSame(collection, CollectionUtils.emptyIfNull(collection));
    }
