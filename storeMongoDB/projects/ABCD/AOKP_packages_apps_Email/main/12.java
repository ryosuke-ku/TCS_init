    public void setReplyTo(Address[] replyTo) throws MessagingException {
        final int REPLY_TO_LENGTH = 10;  // "Reply-to: "
        if (replyTo == null || replyTo.length == 0) {
            removeHeader("Reply-to");
            mReplyTo = null;
        } else {
            setHeader("Reply-to", MimeUtility.fold(Address.toHeader(replyTo), REPLY_TO_LENGTH));
            mReplyTo = replyTo;
        }
    }
