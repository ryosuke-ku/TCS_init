    public void testReadWord() {
        short[] ramData = new short[] { 0, 1, (short) 0177777, (short) 0177776 };
        RandomAccessMemory ram = new RandomAccessMemory("TestRam", 01000, ramData);
        assertEquals(0, ram.read(false, 01000));
        assertEquals(0, ram.read(false, 01001));
        assertEquals(1, ram.read(false, 01002));
        assertEquals(1, ram.read(false, 01003));
        assertEquals(0177777, ram.read(false, 01004));
        assertEquals(0177777, ram.read(false, 01005));
        assertEquals(0177776, ram.read(false, 01006));
        assertEquals(0177776, ram.read(false, 01007));
    }
