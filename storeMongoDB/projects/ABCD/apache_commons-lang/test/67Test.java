    public void testReverseShortRange() {
        short[] array = new short[] {1, 2, 3};
        // The whole array
        ArrayUtils.reverse(array, 0, 3);
        assertEquals(3, array[0]);
        assertEquals(2, array[1]);
        assertEquals(1, array[2]);
        // a range
        array = new short[] {1, 2, 3};
        ArrayUtils.reverse(array, 0, 2);
        assertEquals(2, array[0]);
        assertEquals(1, array[1]);
        assertEquals(3, array[2]);
        // a range with a negative start
        array = new short[] {1, 2, 3};
        ArrayUtils.reverse(array, -1, 3);
        assertEquals(3, array[0]);
        assertEquals(2, array[1]);
        assertEquals(1, array[2]);
        // a range with a large stop idnex
        array = new short[] {1, 2, 3};
        ArrayUtils.reverse(array, -1, array.length + 1000);
        assertEquals(3, array[0]);
        assertEquals(2, array[1]);
        assertEquals(1, array[2]);
        // null
        array = null;
        ArrayUtils.reverse(array, 0, 3);
        assertEquals(null, array);
    }
