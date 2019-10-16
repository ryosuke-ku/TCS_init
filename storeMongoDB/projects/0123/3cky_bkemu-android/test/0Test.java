    public void testCalculateByteArray() throws Exception {
        // According http://reveng.sourceforge.net/crc-catalogue/legend.htm (see "Appendix")
        assertEquals(0x29b1, Crc16.calculate("123456789".getBytes("UTF-8")));
    }
om.read(false, 01000));
        rom.write(true, 01001, 0);
        assertEquals(0377 << 8, rom.read(false, 01000));
    }
