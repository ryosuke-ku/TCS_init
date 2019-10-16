    public void testSimpleBase64() throws Exception {
        byte[] input = IOUtils.toUTF8Bytes("test");

        assertEquals("dGVzdA==", Base64.encodeBytes(input, 0, input.length));
        assertArrayEquals(input, Base64.decode("dGVzdA=="));
        assertArrayEquals(input, Base64.decode(Base64.encodeBytes(input)));
    }
