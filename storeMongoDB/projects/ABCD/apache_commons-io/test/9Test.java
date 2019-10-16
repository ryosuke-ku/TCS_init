    public void testReadSwappedInteger() throws IOException {
        final byte[] bytes = new byte[] { 0x04, 0x03, 0x02, 0x01 };
        assertEquals( 0x01020304, EndianUtils.readSwappedInteger( bytes, 0 ) );

        final ByteArrayInputStream input = new ByteArrayInputStream(bytes);
        assertEquals( 0x01020304, EndianUtils.readSwappedInteger( input ) );
    }
