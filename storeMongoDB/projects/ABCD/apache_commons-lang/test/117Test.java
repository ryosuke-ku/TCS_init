    public void testContainsChar() {
        char[] array = null;
        assertFalse(ArrayUtils.contains(array, 'b'));
        array = new char[] { 'a', 'b', 'c', 'd', 'a' };
        assertTrue(ArrayUtils.contains(array, 'a'));
        assertTrue(ArrayUtils.contains(array, 'b'));
        assertTrue(ArrayUtils.contains(array, 'c'));
        assertTrue(ArrayUtils.contains(array, 'd'));
        assertFalse(ArrayUtils.contains(array, 'e'));
    }
