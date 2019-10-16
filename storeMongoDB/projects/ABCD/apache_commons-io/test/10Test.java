    public void testReadSwappedUnsignedInteger() throws IOException {
        final byte[] bytes = new byte[] { 0x04, 0x03, 0x02, 0x01 };
        assertEquals( 0x0000000001020304L, EndianUtils.readSwappedUnsignedInteger( bytes, 0 ) );

        final ByteArrayInputStream input = new ByteArrayInputStream(bytes);
        assertEquals( 0x0000000001020304L, EndianUtils.readSwappedUnsignedInteger( input ) );
    }
