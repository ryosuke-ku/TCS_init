    public void testExtractDomain() {
        String domain = EditEventHelper.extractDomain("test.email@gmail.com");
        assertEquals("gmail.com", domain);

        domain = EditEventHelper.extractDomain("bademail.no#$%at symbol");
        assertNull(domain);
    }
