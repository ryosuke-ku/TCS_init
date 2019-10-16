    @Test public void extractMessageAttachments() throws Exception {
        Message message = TestUtils.createMockMimeMessage(session, 2);
        List<BodyPart> parts = MessageUtils.extractMessageAttachments(logger, message.getContent());
        assertEquals(2, parts.size());
    }
