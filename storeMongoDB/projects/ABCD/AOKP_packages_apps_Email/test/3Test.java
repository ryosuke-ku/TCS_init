    public void testFoldAndEncode2() {
        String result1 = MimeUtility.foldAndEncode2(SHORT_UNICODE, 10);
        assertEquals(SHORT_UNICODE_ENCODED, result1);
    }
