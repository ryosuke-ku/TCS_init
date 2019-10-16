    public void testSkipWithBOM() throws Exception {
        final byte[] data = new byte[] { 'A', 'B', 'C', 'D' };
        final InputStream in = new BOMInputStream(createUtf8DataStream(data, true));
        in.skip(2L);
        assertEquals('C', in.read());
        in.close();
    }
