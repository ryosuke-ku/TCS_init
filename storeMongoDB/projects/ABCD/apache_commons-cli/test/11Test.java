    public void testHasArgs()
    {
        Option option = new Option("f", null);

        option.setArgs(0);
        assertFalse(option.hasArgs());

        option.setArgs(1);
        assertFalse(option.hasArgs());

        option.setArgs(10);
        assertTrue(option.hasArgs());

        option.setArgs(Option.UNLIMITED_VALUES);
        assertTrue(option.hasArgs());

        option.setArgs(Option.UNINITIALIZED);
        assertFalse(option.hasArgs());
    }
