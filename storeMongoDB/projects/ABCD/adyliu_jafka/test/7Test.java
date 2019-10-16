    public void testCompressionCodec() {
        Message m = new Message("demo".getBytes());
        assertEquals(CompressionCodec.NoCompressionCodec, m.compressionCodec());
    }
