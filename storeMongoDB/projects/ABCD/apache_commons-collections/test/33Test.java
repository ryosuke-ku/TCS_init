    public void indexOf() {
        Predicate<Number> testPredicate = equalPredicate((Number) 4);
        int index = IteratorUtils.indexOf(iterableA.iterator(), testPredicate);
        assertEquals(6, index);
        testPredicate = equalPredicate((Number) 45);
        index = IteratorUtils.indexOf(iterableA.iterator(), testPredicate);
        assertEquals(-1, index);
        assertEquals(-1, IteratorUtils.indexOf(null, testPredicate));
        try {
            assertNull(IteratorUtils.indexOf(iterableA.iterator(), null));
            fail("expecting NullPointerException");
        } catch (final NullPointerException npe) {
            // expected
        }
    }
