    public void testSwapFloatRange() {
        float[] array = new float[] {1, 2, 3, 4};
        ArrayUtils.swap(array, 0, 2, 2);
        assertEquals(3, array[0], 0);
        assertEquals(4, array[1], 0);
        assertEquals(1, array[2], 0);
        assertEquals(2, array[3], 0);

        array = new float[] {1, 2, 3};
        ArrayUtils.swap(array, 0, 3);
        assertEquals(1, array[0], 0);
        assertEquals(2, array[1], 0);
        assertEquals(3, array[2], 0);
        
        array = new float[] {1, 2, 3};
        ArrayUtils.swap(array, 0, 2, 2);
        assertEquals(3, array[0], 0);
        assertEquals(2, array[1], 0);
        assertEquals(1, array[2], 0);
    }
