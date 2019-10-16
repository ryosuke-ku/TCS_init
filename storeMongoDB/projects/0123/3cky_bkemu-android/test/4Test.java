    public void testWriteByte() {
        byte[] ramData = new byte[] { 0, 0 };
        RandomAccessMemory ram = new RandomAccessMemory("TestRam", 01000, ramData);
        ram.write(true, 01000, 1);
        assertEquals(1, ram.read(false, 01000));
        ram.write(true, 01001, 0377);
        assertEquals((0377 << 8) + 1, ram.read(false, 01000));
        ram.write(true, 01001, 0177777);
        assertEquals((0377 << 8) + 1, ram.read(false, 01000));
    }
