    public void testContextMenuWhileEditing() throws InterruptedException {
        final String method = "testContextMenuWhileEditing";
        TestSuite.waitForListLoaded(this, getActivity(), 2);
        openEditor();
        ListActivityTestHelper<TimelineActivity> helper =
                new ListActivityTestHelper<>(this, ConversationActivity.class);
        long msgId = helper.getListItemIdOfLoadedReply();
        String logMsg = "msgId=" + msgId;

        String body = MyQuery.msgIdToStringColumnValue(MsgTable.BODY, msgId);
        helper.invokeContextMenuAction4ListItemId(method, msgId, MessageListContextMenuItem.COPY_TEXT);
        assertEquals(logMsg, body, getClipboardText(method));

        helper.invokeContextMenuAction4ListItemId(method, msgId, MessageListContextMenuItem.COPY_AUTHOR);
        String text = getClipboardText(method);
        assertTrue(logMsg + "; Text: '" + text + "'", text.startsWith("@") && text.lastIndexOf("@") > 1);
    }
