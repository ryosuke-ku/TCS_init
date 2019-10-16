    public void testCreateValueFromInputStream() throws Exception {
        byte[] bytes = new byte[]{'a', 'b'};
        ByteArrayInputStream is = new ByteArrayInputStream(bytes);
        Binary binary = valueFactory.createBinary(is);

        Value v = syncCtx.createValue(is);
        assertNotNull(v);
        assertEquals(PropertyType.BINARY, v.getType());
        assertEquals(binary, v.getBinary());
    }
