    public MessageEditorData addMentionsToText() {
        if (inReplyToId != 0) {
            if (replyAll) {
                addConversationMembersToText();
            } else {
                addMentionedAuthorOfMessageToText(inReplyToId);
            }
        }
        return this;
    }
