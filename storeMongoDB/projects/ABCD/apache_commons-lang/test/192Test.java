    public void testIsNotEmptyObject() {
        final Object[] emptyArray = new Object[] {};
        final Object[] notEmptyArray = new Object[] { new String("Value") };
        assertFalse(ArrayUtils.isNotEmpty((Object[])null));
        assertFalse(ArrayUtils.isNotEmpty(emptyArray));
        assertTrue(ArrayUtils.isNotEmpty(notEmptyArray));
    }
