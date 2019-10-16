    public void testWriteSwappedLong() throws IOException {
        byte[] bytes = new byte[8];
        EndianUtils.writeSwappedLong( bytes, 0, 0x0102030405060708L );
        assertEquals( 0x08, bytes[0] );
        assertEquals( 0x07, bytes[1] );
        assertEquals( 0x06, bytes[2] );
        assertEquals( 0x05, bytes[3] );
        assertEquals( 0x04, bytes[4] );
        assertEquals( 0x03, bytes[5] );
        assertEquals( 0x02, bytes[6] );
        assertEquals( 0x01, bytes[7] );

        final ByteArrayOutputStream baos = new ByteArrayOutputStream(8);
        EndianUtils.writeSwappedLong( baos, 0x0102030405060708L );
        bytes = baos.toByteArray();
        assertEquals( 0x08, bytes[0] );
        assertEquals( 0x07, bytes[1] );
        assertEquals( 0x06, bytes[2] );
        assertEquals( 0x05, bytes[3] );
        assertEquals( 0x04, bytes[4] );
        assertEquals( 0x03, bytes[5] );
        assertEquals( 0x02, bytes[6] );
        assertEquals( 0x01, bytes[7] );
    }
