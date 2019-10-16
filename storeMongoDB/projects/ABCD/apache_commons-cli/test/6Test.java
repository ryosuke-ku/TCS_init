    public void testClone()
    {
        TestOption a = new TestOption("a", true, "");
        TestOption b = (TestOption) a.clone();
        assertEquals(a, b);
        assertNotSame(a, b);
        a.setDescription("a");
        assertEquals("", b.getDescription());
        b.setArgs(2);
        b.addValue("b1");
        b.addValue("b2");
        assertEquals(1, a.getArgs());
        assertEquals(0, a.getValuesList().size());
        assertEquals(2, b.getValues().length);
    }
