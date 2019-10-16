    public void testSharePlainText() {
        String body = "Posting as a plain Text " + TestSuite.TESTRUN_UID;
        final MyAccount myAccount = TestSuite.getMyAccount(TestSuite.TWITTER_TEST_ACCOUNT_NAME);
        long msgId = MessageInserter.addMessageForAccount(myAccount, body,
                TestSuite.PLAIN_TEXT_MESSAGE_OID, DownloadStatus.LOADED);
        MessageShare messageShare = new MessageShare(myAccount.getOrigin(), msgId, null);
        Intent intent = messageShare.intentToViewAndShare(true);
        assertTrue(intent.getExtras().containsKey(Intent.EXTRA_TEXT));
        assertTrue(
                intent.getStringExtra(Intent.EXTRA_TEXT),
                intent.getStringExtra(Intent.EXTRA_TEXT).contains(body));
        assertFalse(intent.getExtras().containsKey(Intent.EXTRA_HTML_TEXT));
    }
