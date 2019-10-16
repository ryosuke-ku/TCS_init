    public void testClear()
    {
        TestOption option = new TestOption("x", true, "");
        assertEquals(0, option.getValuesList().size());
        option.addValue("a");
        assertEquals(1, option.getValuesList().size());
        option.clearValues();
        assertEquals(0, option.getValuesList().size());
    }
