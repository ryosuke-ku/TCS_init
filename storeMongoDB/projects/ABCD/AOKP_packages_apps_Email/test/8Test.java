    public void testGetTextFromPartContentTypeCase() throws MessagingException {
        final String theText = "This is the text of the part";
        TextBody tb = new TextBody(theText);
        MimeBodyPart p = new MimeBodyPart();
        
        // 1. test basic text/plain mode
        p.setHeader(MimeHeader.HEADER_CONTENT_TYPE, "text/plain");
        p.setBody(tb);
        String gotText = MimeUtility.getTextFromPart(p);
        assertEquals(theText, gotText);
        
        // 2. mixed case is OK
        p.setHeader(MimeHeader.HEADER_CONTENT_TYPE, "TEXT/PLAIN");
        p.setBody(tb);
        gotText = MimeUtility.getTextFromPart(p);
        assertEquals(theText, gotText);
        
        // 3. wildcards OK
        p.setHeader(MimeHeader.HEADER_CONTENT_TYPE, "text/other");
        p.setBody(tb);
        gotText = MimeUtility.getTextFromPart(p);
        assertEquals(theText, gotText);
    }
