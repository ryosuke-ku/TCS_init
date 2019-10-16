    public void testCreateValuesSkipsNull() throws Exception {
        List<String> strings = Lists.newArrayList("s", null, null, "t");
        Value[] vs = syncCtx.createValues(strings);
        assertNotNull(vs);
        assertEquals(2, vs.length);
    }
