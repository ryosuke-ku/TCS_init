    public void testSerializeTo() {
        Message m = new Message("demo".getBytes());
        ByteBuffer buffer = ByteBuffer.allocate(m.serializedSize());
        m.serializeTo(buffer);
        assertFalse(buffer.hasRemaining());
    }
