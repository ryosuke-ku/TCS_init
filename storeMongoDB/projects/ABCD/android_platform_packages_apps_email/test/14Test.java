    public void testDeserializeFromJSON() throws JSONException {
        final JSONObject json = new JSONObject();
        json.put(EmailContent.AccountColumns.DISPLAY_NAME, "David Hasselhoff");
        json.put(EmailContent.AccountColumns.EMAIL_ADDRESS, "dhoff@example.com");
        json.put(EmailContent.AccountColumns.SYNC_LOOKBACK, 42);
        json.put(EmailContent.AccountColumns.SYNC_INTERVAL, 99);
        json.put(Account.JSON_TAG_HOST_AUTH_RECV, getHostAuthJSON("receiver", "recpass").toJson());
        json.put(Account.JSON_TAG_HOST_AUTH_SEND, getHostAuthJSON("send", "sendpass").toJson());
        json.put(EmailContent.AccountColumns.FLAGS, 22);
        json.put(EmailContent.AccountColumns.SENDER_NAME, "Friend of Kitt");
        json.put(EmailContent.AccountColumns.PROTOCOL_VERSION, "protocol version 3.14");
        json.put(EmailContent.AccountColumns.SIGNATURE, "David with a heart over the i");
        json.put(EmailContent.AccountColumns.PING_DURATION, 77);

        // deserialize the json
        final Account a = Account.fromJson(json);

        // verify that all fields deserialized as expected
        assertEquals("David Hasselhoff", a.getDisplayName());
        assertEquals("dhoff@example.com", a.getEmailAddress());
        assertEquals(42, a.getSyncLookback());
        assertEquals(99, a.getSyncInterval());
        assertEquals("receiver", a.mHostAuthRecv.mLogin);
        assertEquals("recpass", a.mHostAuthRecv.mPassword);
        assertEquals("send", a.mHostAuthSend.mLogin);
        assertEquals("sendpass", a.mHostAuthSend.mPassword);
        assertEquals(22, a.getFlags());
        assertEquals("Friend of Kitt", a.getSenderName());
        assertEquals("protocol version 3.14", a.mProtocolVersion);
        assertEquals("David with a heart over the i", a.getSignature());
        assertEquals(77, a.mPingDuration);
    }
