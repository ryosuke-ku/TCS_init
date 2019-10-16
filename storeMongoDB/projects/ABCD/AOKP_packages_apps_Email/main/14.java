    public String getMessageId() throws MessagingException {
        String messageId = getFirstHeader("Message-ID");
        if (messageId == null && !mInhibitLocalMessageId) {
            messageId = generateMessageId();
            setMessageId(messageId);
        }
        return messageId;
    }
