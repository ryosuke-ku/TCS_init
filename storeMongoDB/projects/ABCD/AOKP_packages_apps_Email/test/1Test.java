    public void testDecodeSimple() {
        String result1 = MimeUtility.decode(SHORT_UNICODE_ENCODED);
        assertEquals(SHORT_UNICODE, result1);
    }
