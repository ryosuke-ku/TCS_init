    public void testUnmodifiableCollection() {
        Collection<Object> col = CollectionUtils.unmodifiableCollection(new ArrayList<Object>());
        assertTrue("Returned object should be a UnmodifiableCollection.", col instanceof UnmodifiableCollection);
        try {
            CollectionUtils.unmodifiableCollection(null);
            fail("Expecting NullPointerException for null collection.");
        } catch (final NullPointerException ex) {
            // expected
        }
    }
