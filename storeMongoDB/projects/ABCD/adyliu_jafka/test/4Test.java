    public void testMagic() {
        Message m = new Message("demo".getBytes());
        assertEquals(1,m.magic());
    }
