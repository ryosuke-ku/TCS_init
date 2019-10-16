    public void testMimeTypeMatches() {
        // 1. No match
        assertFalse(MimeUtility.mimeTypeMatches("foo/bar", "TEXT/PLAIN"));
        
        // 2. Match
        assertTrue(MimeUtility.mimeTypeMatches("text/plain", "text/plain"));
        
        // 3. Match (mixed case)
        assertTrue(MimeUtility.mimeTypeMatches("text/plain", "TEXT/PLAIN"));
        assertTrue(MimeUtility.mimeTypeMatches("TEXT/PLAIN", "text/plain"));
        
        // 4. Match (wildcards)
        assertTrue(MimeUtility.mimeTypeMatches("text/plain", "*/plain"));
        assertTrue(MimeUtility.mimeTypeMatches("text/plain", "text/*"));
        assertTrue(MimeUtility.mimeTypeMatches("text/plain", "*/*"));
        
        // 5. No Match (wildcards)
        assertFalse(MimeUtility.mimeTypeMatches("foo/bar", "*/plain"));
        assertFalse(MimeUtility.mimeTypeMatches("foo/bar", "text/*"));
    }
