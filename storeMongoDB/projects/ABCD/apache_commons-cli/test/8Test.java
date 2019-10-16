    public void testHasArgName()
    {
        Option option = new Option("f", null);

        option.setArgName(null);
        assertFalse(option.hasArgName());

        option.setArgName("");
        assertFalse(option.hasArgName());

        option.setArgName("file");
        assertTrue(option.hasArgName());
    }
