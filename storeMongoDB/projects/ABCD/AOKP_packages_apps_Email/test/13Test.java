    public void testMessageId() throws MessagingException {

        // Test 1.  Every message gets a default and unique message-id
        MimeMessage message1 = new MimeMessage();
        MimeMessage message2 = new MimeMessage();
        String id1 = message1.getMessageId();
        String id2 = message2.getMessageId();
        assertNotNull(id1);
        assertNotNull(id2);
        assertFalse("Message-ID should be unique", id1.equals(id2));

        // Test 2.  Set and get using API
        final String testId1 = "test-message-id-one";
        message1.setMessageId(testId1);
        assertEquals("set and get Message-ID", testId1, message1.getMessageId());

        // Test 3.  Should only be one Message-ID per message
        final String testId2 = "test-message-id-two";
        message2.setMessageId(testId1);
        message2.setMessageId(testId2);
        assertEquals("set and get Message-ID", testId2, message2.getMessageId());
    }
