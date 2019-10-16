    public void testHierarchyIncludingInterfaces() {
        final Iterator<Class<?>> iter =
            ClassUtils.hierarchy(StringParameterizedChild.class, Interfaces.INCLUDE).iterator();
        assertEquals(StringParameterizedChild.class, iter.next());
        assertEquals(GenericParent.class, iter.next());
        assertEquals(GenericConsumer.class, iter.next());
        assertEquals(Object.class, iter.next());
        assertFalse(iter.hasNext());
    }
