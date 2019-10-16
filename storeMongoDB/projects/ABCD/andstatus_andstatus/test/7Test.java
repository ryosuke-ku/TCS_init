    private void assertData(MyAccount ma, long inReplyToMsgId, long inReplyToUserId, long recipientId,
            long memberUserId, boolean replyAll) {
        Uri uri = Uri.parse("http://example.com/" + TestSuite.TESTRUN_UID + "/some.png");
        MessageEditorData data = MessageEditorData.newEmpty(ma)
                .setInReplyToId(inReplyToMsgId)
                .setRecipientId(recipientId)
                .setReplyAll(replyAll)
                .setBody("Some text here " + TestSuite.TESTRUN_UID);
        assertFalse(data.toString(), data.body.contains("@"));
        data.addMentionsToText();
        assertEquals(recipientId, data.recipientId);
        assertMentionedUser(data, inReplyToUserId, true);
        assertMentionedUser(data, memberUserId, replyAll);
        assertEquals(data.toString(), Uri.EMPTY, data.getMediaUri());
    }
