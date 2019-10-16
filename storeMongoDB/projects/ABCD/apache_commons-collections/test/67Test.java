    public void testRetainAllWithEquator() {
        final List<String> base = new ArrayList<String>();
        base.add("AC");
        base.add("BB");
        base.add("CA");

        final List<String> retain = new ArrayList<String>();
        retain.add("AA");
        retain.add("CX");
        retain.add("XZ");

        // use an equator which compares the second letter only
        final Collection<String> result = CollectionUtils.retainAll(base, retain, new Equator<String>() {

            @Override
            public boolean equate(String o1, String o2) {
                return o1.charAt(1) == o2.charAt(1);
            }

            @Override
            public int hash(String o) {
                return o.charAt(1);
            }
        });
        assertEquals(1, result.size());
        assertTrue(result.contains("CA"));
        assertFalse(result.contains("BB"));
        assertFalse(result.contains("AC"));

        assertEquals(3, base.size());
        assertTrue(base.contains("AC"));
        assertTrue(base.contains("BB"));
        assertTrue(base.contains("CA"));

        assertEquals(3, retain.size());
        assertTrue(retain.contains("AA"));
        assertTrue(retain.contains("CX"));
        assertTrue(retain.contains("XZ"));

        try {
            CollectionUtils.retainAll(null, null, null);
            fail("expecting NullPointerException");
        } catch (final NullPointerException npe) {
        } // this is what we want

        try {
            CollectionUtils.retainAll(base, retain, null);
            fail("expecting NullPointerException");
        } catch (final NullPointerException npe) {
        } // this is what we want
    }
