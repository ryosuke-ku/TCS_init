    public void testSupportCode() throws Exception {
        final InputStream in = createUtf8DataStream(new byte[] { 'A', 'B' }, true);
        final byte[] buf = new byte[1024];
        final int len = in.read(buf);
        assertEquals(5, len);
        assertEquals(0xEF, buf[0] & 0xFF);
        assertEquals(0xBB, buf[1] & 0xFF);
        assertEquals(0xBF, buf[2] & 0xFF);
        assertEquals('A', buf[3] & 0xFF);
        assertEquals('B', buf[4] & 0xFF);

        assertData(
                new byte[] { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF, 'A', 'B' },
                buf, len);
    }
