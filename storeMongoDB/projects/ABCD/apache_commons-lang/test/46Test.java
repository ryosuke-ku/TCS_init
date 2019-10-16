    public void testSameLengthInt() {
        final int[] nullArray = null;
        final int[] emptyArray = new int[0];
        final int[] oneArray = new int[] {4};
        final int[] twoArray = new int[] {5, 7};
        
        assertTrue(ArrayUtils.isSameLength(nullArray, nullArray));
        assertTrue(ArrayUtils.isSameLength(nullArray, emptyArray));
        assertFalse(ArrayUtils.isSameLength(nullArray, oneArray));
        assertFalse(ArrayUtils.isSameLength(nullArray, twoArray));
        
        assertTrue(ArrayUtils.isSameLength(emptyArray, nullArray));
        assertTrue(ArrayUtils.isSameLength(emptyArray, emptyArray));
        assertFalse(ArrayUtils.isSameLength(emptyArray, oneArray));
        assertFalse(ArrayUtils.isSameLength(emptyArray, twoArray));
        
        assertFalse(ArrayUtils.isSameLength(oneArray, nullArray));
        assertFalse(ArrayUtils.isSameLength(oneArray, emptyArray));
        assertTrue(ArrayUtils.isSameLength(oneArray, oneArray));
        assertFalse(ArrayUtils.isSameLength(oneArray, twoArray));
        
        assertFalse(ArrayUtils.isSameLength(twoArray, nullArray));
        assertFalse(ArrayUtils.isSameLength(twoArray, emptyArray));
        assertFalse(ArrayUtils.isSameLength(twoArray, oneArray));
        assertTrue(ArrayUtils.isSameLength(twoArray, twoArray));
    }
