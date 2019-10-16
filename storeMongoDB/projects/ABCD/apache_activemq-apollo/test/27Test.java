    private static <T> void assertIteratorEquals(Iterator<T> i1, Iterator<T> i2)
    {
        assertEquals(i1.hasNext(), i2.hasNext());
        if(i1.hasNext())
        {
            assertEquals(i1.next(), i2.next());
        }
    }
