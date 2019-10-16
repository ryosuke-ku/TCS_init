    public void testAttributes() {
        Message m = new Message("demo".getBytes());
        assertEquals((byte)0,m.attributes());
    }
