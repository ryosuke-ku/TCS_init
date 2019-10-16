    public void testReadSwappedShort() throws IOException {
        final byte[] bytes = new byte[] { 0x02, 0x01 };
        assertEquals( 0x0102, EndianUtils.readSwappedShort( bytes, 0 ) );

        final ByteArrayInputStream input = new ByteArrayInputStream(bytes);
        assertEquals( 0x0102, EndianUtils.readSwappedShort( input ) );
    }
