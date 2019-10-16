    public void testGetContentId() throws MessagingException {
        MimeMessage message = new MimeMessage();

        // no content-id
        assertNull(message.getContentId());

        // normal case
        final String cid1 = "cid.1@android.com";
        message.setHeader(MimeHeader.HEADER_CONTENT_ID, cid1);
        assertEquals(cid1, message.getContentId());

        // surrounded by optional bracket
        message.setHeader(MimeHeader.HEADER_CONTENT_ID, "<" + cid1 + ">");
        assertEquals(cid1, message.getContentId());
    }
