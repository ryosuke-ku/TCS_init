    public void testSynchronizedCollection() {
        Collection<Object> col = CollectionUtils.synchronizedCollection(new ArrayList<Object>());
        assertTrue("Returned object should be a SynchronizedCollection.", col instanceof SynchronizedCollection);
        try {
            CollectionUtils.synchronizedCollection(null);
            fail("Expecting NullPointerException for null collection.");
        } catch (final NullPointerException ex) {
            // expected
        }
    }
