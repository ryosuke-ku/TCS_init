    public void testUTF8() {
        StringBuilder sb = new StringBuilder();
        for (char c = ' '; c <= 0xfff; c++) {
            sb.append(c);
        }

        assertEquals(sb.toString(), IOUtils.toUTF8String(IOUtils.toUTF8Bytes(sb.toString())));
        assertNull(IOUtils.toUTF8Bytes(null));
        assertNull(IOUtils.toUTF8String(null));
    }
