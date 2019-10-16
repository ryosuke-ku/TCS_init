    public void testLongToByteArray() throws Exception
        {
        byte[] bytes = BitUtils.toByteArray(1L);
        assertEquals(1L, BitUtils.byteArrayToLong(bytes));

        assertEquals(0, bytes[0]);
        assertEquals(0, bytes[1]);
        assertEquals(0, bytes[2]);
        assertEquals(0, bytes[3]);
        assertEquals(0, bytes[4]);
        assertEquals(0, bytes[5]);
        assertEquals(0, bytes[6]);
        assertEquals(1, bytes[7]);

        bytes = BitUtils.toByteArray(256L);
        assertEquals(256L, BitUtils.byteArrayToLong(bytes));

        assertEquals(0, bytes[0]);
        assertEquals(0, bytes[1]);
        assertEquals(0, bytes[2]);
        assertEquals(0, bytes[3]);
        assertEquals(0, bytes[4]);
        assertEquals(0, bytes[5]);
        assertEquals(1, bytes[6]);
        assertEquals(0, bytes[7]);

        bytes = BitUtils.toByteArray(127L);
        assertEquals(127L, BitUtils.byteArrayToLong(bytes));

        assertEquals(0, bytes[0]);
        assertEquals(0, bytes[1]);
        assertEquals(0, bytes[2]);
        assertEquals(0, bytes[3]);
        assertEquals(0, bytes[4]);
        assertEquals(0, bytes[5]);
        assertEquals(0, bytes[6]);
        assertEquals(127, bytes[7]);

        bytes = BitUtils.toByteArray(0xffffL);
        assertEquals(0xffffL, BitUtils.byteArrayToLong(bytes));

        assertEquals(0, bytes[0]);
        assertEquals(0, bytes[1]);
        assertEquals(0, bytes[2]);
        assertEquals(0, bytes[3]);
        assertEquals(0, bytes[4]);
        assertEquals(0, bytes[5]);
        assertEquals(-1, bytes[6]);
        assertEquals(-1, bytes[7]);

        bytes = BitUtils.toByteArray(0x01ffffL);
        assertEquals(0x01ffff, BitUtils.byteArrayToLong(bytes));

        assertEquals(0, bytes[0]);
        assertEquals(0, bytes[1]);
        assertEquals(0, bytes[2]);
        assertEquals(0, bytes[3]);
        assertEquals(0, bytes[4]);
        assertEquals(1, bytes[5]);
        assertEquals(-1, bytes[6]);
        assertEquals(-1, bytes[7]);

        bytes = BitUtils.toByteArray(0x09ffffffL);
        assertEquals(0x09ffffffL, BitUtils.byteArrayToLong(bytes));

        assertEquals(0, bytes[0]);
        assertEquals(0, bytes[1]);
        assertEquals(0, bytes[2]);
        assertEquals(0, bytes[3]);
        assertEquals(9, bytes[4]);
        assertEquals(-1, bytes[5]);
        assertEquals(-1, bytes[6]);
        assertEquals(-1, bytes[7]);


        bytes = BitUtils.toByteArray(0x09ffffffffL);
        assertEquals(0x09ffffffffL, BitUtils.byteArrayToLong(bytes));

        assertEquals(0, bytes[0]);
        assertEquals(0, bytes[1]);
        assertEquals(0, bytes[2]);
        assertEquals(9, bytes[3]);
        assertEquals(-1, bytes[4]);
        assertEquals(-1, bytes[5]);
        assertEquals(-1, bytes[6]);
        assertEquals(-1, bytes[7]);


        }
