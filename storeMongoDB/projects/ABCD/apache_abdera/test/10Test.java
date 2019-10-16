    public void testIsMimeType() {
        assertTrue(MimeTypeHelper.isMimeType("text/html"));
        assertTrue(MimeTypeHelper.isMimeType("*/*"));
        assertTrue(MimeTypeHelper.isMimeType("blafasel/pdf"));
        assertFalse(MimeTypeHelper.isMimeType("text"));
    }
