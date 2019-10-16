    public void testToArray()
    {
        Options options = new Options();
        options.add(Integer.valueOf(1), "txt", false);
        options.add(Integer.valueOf(2), "txt2", false);
        assertEquals(2, options.toArray().length);
    }
