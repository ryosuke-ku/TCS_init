    public void testUnfoldAndDecodeSimple() {
        String result1 = MimeUtility.unfoldAndDecode(SHORT_UNICODE_ENCODED);
        assertEquals(SHORT_UNICODE, result1);
    }
