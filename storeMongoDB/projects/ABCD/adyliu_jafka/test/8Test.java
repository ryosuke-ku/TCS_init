    public void testChecksum() {
        Message m = new Message("demo".getBytes());
        assertEquals(Utils.crc32("demo".getBytes()), m.checksum());
    }
