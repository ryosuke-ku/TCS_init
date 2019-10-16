    public void testPayloadSize() {
        Message m = new Message("demo".getBytes());
        assertEquals(4,m.payloadSize());
    }
