    public void test_basic() {
        ListIntSet set = new ListIntSet();

        assertEquals(0, set.elements());

        set.add(31);
        set.add(0);
        set.add(1);

        assertTrue(set.has(0));
        assertTrue(set.has(1));
        assertTrue(set.has(31));

        assertEquals(3, set.elements());

        assertFalse(set.has(2));
        assertFalse(set.has(7));
        assertFalse(set.has(30));
    }
