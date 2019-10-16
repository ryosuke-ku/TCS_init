    public void testWriteSwappedInteger() throws IOException {
        byte[] bytes = new byte[4];
        EndianUtils.writeSwappedInteger( bytes, 0, 0x01020304 );
        assertEquals( 0x04, bytes[0] );
        assertEquals( 0x03, bytes[1] );
        assertEquals( 0x02, bytes[2] );
        assertEquals( 0x01, bytes[3] );

        final ByteArrayOutputStream baos = new ByteArrayOutputStream(4);
        EndianUtils.writeSwappedInteger( baos, 0x01020304 );
        bytes = baos.toByteArray();
        assertEquals( 0x04, bytes[0] );
        assertEquals( 0x03, bytes[1] );
        assertEquals( 0x02, bytes[2] );
        assertEquals( 0x01, bytes[3] );
    }
