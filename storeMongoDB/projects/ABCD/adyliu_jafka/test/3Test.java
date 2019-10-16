    public void testGetSizeInBytes() {
        Message m = new Message("demo".getBytes());
        assertEquals(6+4, m.getSizeInBytes());
    }
