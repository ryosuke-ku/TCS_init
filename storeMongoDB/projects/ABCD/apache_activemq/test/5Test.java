    public void testAMQPHeaderReadEmptyBuffer() throws Exception {
        codec.parse(ByteBuffer.allocate(0));
    }
