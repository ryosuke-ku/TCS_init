    public void testToObject_int() {
        final int[] b = null;
        assertArrayEquals(null, ArrayUtils.toObject(b));
    
        assertSame(
            ArrayUtils.EMPTY_INTEGER_OBJECT_ARRAY,
            ArrayUtils.toObject(new int[0]));
    
        assertTrue(
            Arrays.equals(
                new Integer[] {
                    Integer.valueOf(Integer.MIN_VALUE),
                    Integer.valueOf(Integer.MAX_VALUE),
                    Integer.valueOf(9999999)},
            ArrayUtils.toObject(
                new int[] { Integer.MIN_VALUE, Integer.MAX_VALUE, 9999999 })));
    }
