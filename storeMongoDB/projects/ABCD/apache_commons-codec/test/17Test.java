    public void testEncodeHexByteString_ByteBufferBoolean_ToUpperCase() {
        assertEquals("0A", Hex.encodeHexString(ByteBuffer.wrap(new byte[] { 10 }), false));
    }
