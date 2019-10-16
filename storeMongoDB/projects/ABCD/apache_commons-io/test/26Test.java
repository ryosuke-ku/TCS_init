    public void testReadSwappedFloat() throws IOException {
        final byte[] bytes = new byte[] { 0x04, 0x03, 0x02, 0x01 };
        final float f1 = Float.intBitsToFloat( 0x01020304 );
        final float f2 = EndianUtils.readSwappedFloat( bytes, 0 );
        assertEquals( f1, f2, 0.0 );

        final ByteArrayInputStream input = new ByteArrayInputStream(bytes);
        assertEquals( f1, EndianUtils.readSwappedFloat( input ), 0.0 );
    }
