    public void testIsSortedShort() {
        short[] array = null;
        assertTrue(ArrayUtils.isSorted(array));

        array = new short[]{0};
        assertTrue(ArrayUtils.isSorted(array));

        array = new short[]{-1, 0, 1};
        assertTrue(ArrayUtils.isSorted(array));

        array = new short[]{-1, 1, 0};
        assertFalse(ArrayUtils.isSorted(array));
    }
