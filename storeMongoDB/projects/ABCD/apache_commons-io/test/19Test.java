    public void testReadSwappedUnsignedShort() throws IOException {
        final byte[] bytes = new byte[] { 0x02, 0x01 };
        assertEquals( 0x00000102, EndianUtils.readSwappedUnsignedShort( bytes, 0 ) );

        final ByteArrayInputStream input = new ByteArrayInputStream(bytes);
        assertEquals( 0x00000102, EndianUtils.readSwappedUnsignedShort( input ) );
    }
