    public void testOptionsOptions()
    {
        Options options = new Options();
        options.add(new Object(), "text", true);
        Options options2 = new Options(options);
        assertEquals(1, options2.size());
    }
