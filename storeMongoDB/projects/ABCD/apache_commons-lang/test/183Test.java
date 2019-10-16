    public void testIsEmptyObject() {
        final Object[] emptyArray = new Object[] {};
        final Object[] notEmptyArray = new Object[] { new String("Value") };
        assertTrue(ArrayUtils.isEmpty((Object[])null));
        assertTrue(ArrayUtils.isEmpty(emptyArray));
        assertFalse(ArrayUtils.isEmpty(notEmptyArray));
    }
