    public void testShiftRangeInt() {
        final int[] array = new int[] {1, 2, 3, 4, 5};
        ArrayUtils.shift(array, 1, 3, 1);
        assertEquals(1, array[0]);
        assertEquals(3, array[1]);
        assertEquals(2, array[2]);
        assertEquals(4, array[3]);
        assertEquals(5, array[4]);
        ArrayUtils.shift(array, 1, 4, 2);
        assertEquals(1, array[0]);
        assertEquals(2, array[1]);
        assertEquals(4, array[2]);
        assertEquals(3, array[3]);
        assertEquals(5, array[4]);
    }
