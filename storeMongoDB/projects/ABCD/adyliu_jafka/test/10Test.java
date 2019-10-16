    public void testIsValid() {
        Message m = new Message("demo".getBytes());
        ByteBuffer buf = m.buffer;
        assertTrue(m.isValid());
        buf.put(buf.limit()-1, (byte)(1+ buf.get(buf.limit()-1)));
        assertFalse(m.isValid());
    }
