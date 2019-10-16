    public void testWriteSwappedShort() throws IOException {
        byte[] bytes = new byte[2];
        EndianUtils.writeSwappedShort( bytes, 0, (short) 0x0102 );
        assertEquals( 0x02, bytes[0] );
        assertEquals( 0x01, bytes[1] );

        final ByteArrayOutputStream baos = new ByteArrayOutputStream(2);
        EndianUtils.writeSwappedShort( baos, (short) 0x0102 );
        bytes = baos.toByteArray();
        assertEquals( 0x02, bytes[0] );
        assertEquals( 0x01, bytes[1] );
    }
