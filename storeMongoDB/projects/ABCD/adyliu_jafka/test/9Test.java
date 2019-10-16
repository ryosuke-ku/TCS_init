    public void testPayload() {
        Message m = new Message("demo".getBytes());
        assertEquals(ByteBuffer.wrap("demo".getBytes()), m.payload());
    }
