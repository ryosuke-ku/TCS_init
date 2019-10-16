    public void testWriteSwappedFloat() throws IOException {
        byte[] bytes = new byte[4];
        final float f1 = Float.intBitsToFloat( 0x01020304 );
        EndianUtils.writeSwappedFloat( bytes, 0, f1 );
        assertEquals( 0x04, bytes[0] );
        assertEquals( 0x03, bytes[1] );
        assertEquals( 0x02, bytes[2] );
        assertEquals( 0x01, bytes[3] );

        final ByteArrayOutputStream baos = new ByteArrayOutputStream(4);
        EndianUtils.writeSwappedFloat( baos, f1 );
        bytes = baos.toByteArray();
        assertEquals( 0x04, bytes[0] );
        assertEquals( 0x03, bytes[1] );
        assertEquals( 0x02, bytes[2] );
        assertEquals( 0x01, bytes[3] );
    }
