    public void testMsb() {
        assertEquals(0, Data.width(0));
        assertEquals(1, Data.width(1));
        assertEquals(2, Data.width(2));
        assertEquals(2, Data.width(3));
        assertEquals(3, Data.width(4));
        assertEquals(4, Data.width(12));
        assertEquals(5, Data.width(25));
        assertEquals(10, Data.width(1023));
        assertEquals(11, Data.width(1025));
        assertEquals(11, Data.width(1025));
        assertEquals(26, Data.width((1 << 25) + (1 << 23) + (1 << 12) + (1 << 3 + 1)));
    }
