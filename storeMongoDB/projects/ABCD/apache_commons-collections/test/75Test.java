    public void extractSingleton() {
        ArrayList<String> coll = null;
        try {
            CollectionUtils.extractSingleton(coll);
            fail("expected NullPointerException from extractSingleton(null)");
        } catch (final NullPointerException e) {
        }
        coll = new ArrayList<String>();
        try {
            CollectionUtils.extractSingleton(coll);
            fail("expected IllegalArgumentException from extractSingleton(empty)");
        } catch (final IllegalArgumentException e) {
        }
        coll.add("foo");
        assertEquals("foo", CollectionUtils.extractSingleton(coll));
        coll.add("bar");
        try {
            CollectionUtils.extractSingleton(coll);
            fail("expected IllegalArgumentException from extractSingleton(size == 2)");
        } catch (final IllegalArgumentException e) {
        }
    }
