    @Test public void extractInlineAttachments() throws Exception {
        Message message = TestUtils.createMockMimeMessage(session, 1);

        List<BodyPart> attachments = MessageUtils.extractMessageAttachments(logger, message.getContent());
        List<BodyPart> inlineImgs = MessageUtils.extractInlineImages(logger, message.getContent());
        assertEquals(1, attachments.size());
        assertEquals(0, inlineImgs.size());

        TestUtils.addMockAttachment(message, "mfile.bin", false);

        attachments = MessageUtils.extractMessageAttachments(logger, message.getContent());
        inlineImgs = MessageUtils.extractInlineImages(logger, message.getContent());
        assertEquals(2, attachments.size());
        assertEquals(0, inlineImgs.size());

        TestUtils.addMockAttachment(message, "mfile.jpg", true);

        attachments = MessageUtils.extractMessageAttachments(logger, message.getContent());
        inlineImgs = MessageUtils.extractInlineImages(logger, message.getContent());
        assertEquals(3, attachments.size());
        assertEquals(1, inlineImgs.size());
    }
