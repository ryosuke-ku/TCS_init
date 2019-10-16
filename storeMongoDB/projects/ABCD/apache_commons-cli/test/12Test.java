    public void testGetValue()
    {
        Option option = new Option("f", null);
        option.setArgs(Option.UNLIMITED_VALUES);

        assertEquals("default", option.getValue("default"));
        assertEquals(null, option.getValue(0));

        option.addValueForProcessing("foo");
        
        assertEquals("foo", option.getValue());
        assertEquals("foo", option.getValue(0));
        assertEquals("foo", option.getValue("default"));
    }
