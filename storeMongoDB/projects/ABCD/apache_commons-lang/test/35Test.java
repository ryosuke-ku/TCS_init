    public void testByteToHex() {
        assertEquals("", Conversion.byteToHex((byte)0x00, 0, "", 0, 0));
        assertEquals("", Conversion.byteToHex((byte)0x00, 100, "", 0, 0));
        assertEquals("", Conversion.byteToHex((byte)0x00, 0, "", 100, 0));
        assertEquals("00000", Conversion.byteToHex((byte)0xEF, 0, "00000", 0, 0));
        assertEquals("f0000", Conversion.byteToHex((byte)0xEF, 0, "00000", 0, 1));
        assertEquals("fe000", Conversion.byteToHex((byte)0xEF, 0, "00000", 0, 2));
        assertEquals("000f0", Conversion.byteToHex((byte)0xEF, 0, "00000", 3, 1));
        assertEquals("000fe", Conversion.byteToHex((byte)0xEF, 0, "00000", 3, 2));
        assertEquals("70000", Conversion.byteToHex((byte)0xEF, 1, "00000", 0, 1));
        assertEquals("b0000", Conversion.byteToHex((byte)0xEF, 2, "00000", 0, 1));
        assertEquals("000df", Conversion.byteToHex((byte)0xEF, 3, "00000", 3, 2));
        // assertEquals("00000",Conversion.byteToHex((byte)0xEF, 4,"00000",3,2));//rejected by
        // assertion
        assertEquals("000e0", Conversion.byteToHex((byte)0xEF, 4, "00000", 3, 1));
        assertEquals("fe", Conversion.byteToHex((byte)0xEF, 0, "", 0, 2));
        try {
            Conversion.byteToHex((byte)0xEF, 0, "", 1, 2);
            fail("Thrown " + StringIndexOutOfBoundsException.class.getName() + " expected");
        } catch (final StringIndexOutOfBoundsException e) {
            // OK
        }
    }
