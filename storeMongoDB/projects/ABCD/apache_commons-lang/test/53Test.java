    public void testSameType() {
        try {
            ArrayUtils.isSameType(null, null);
            fail();
        } catch (final IllegalArgumentException ex) {}
        try {
            ArrayUtils.isSameType(null, new Object[0]);
            fail();
        } catch (final IllegalArgumentException ex) {}
        try {
            ArrayUtils.isSameType(new Object[0], null);
            fail();
        } catch (final IllegalArgumentException ex) {}
        
        assertTrue(ArrayUtils.isSameType(new Object[0], new Object[0]));
        assertFalse(ArrayUtils.isSameType(new String[0], new Object[0]));
        assertTrue(ArrayUtils.isSameType(new String[0][0], new String[0][0]));
        assertFalse(ArrayUtils.isSameType(new String[0], new String[0][0]));
        assertFalse(ArrayUtils.isSameType(new String[0][0], new String[0]));
    }
