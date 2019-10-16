    public void testReadSwappedLong() throws IOException {
        final byte[] bytes = new byte[] { 0x08, 0x07, 0x06, 0x05, 0x04, 0x03, 0x02, 0x01 };
        assertEquals( 0x0102030405060708L, EndianUtils.readSwappedLong( bytes, 0 ) );

        final ByteArrayInputStream input = new ByteArrayInputStream(bytes);
        assertEquals( 0x0102030405060708L, EndianUtils.readSwappedLong( input ) );
    }
