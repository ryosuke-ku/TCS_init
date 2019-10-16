    public void testSerializeAndDeserializeWithJSON() {
        // build an Account object with all fields set
        final Account before = new Account();
        before.setDisplayName("David Hasselhoff");
        before.setEmailAddress("dhoff@example.com");
        before.mSyncKey = "syncKey";
        before.setSyncLookback(42);
        before.setSyncInterval(99);
        before.setFlags(1 << 5);
        before.setSenderName("Friend of Kitt");
        before.mProtocolVersion = "protocol version 3.14";
        before.mSecuritySyncKey = "securitySyncKey";
        before.setSignature("David with a heart over the i");
        before.mPolicyKey = 66;
        before.mPingDuration = 77;
        before.mHostAuthRecv = getHostAuthJSON("receiver", "recpass");

        // this must be called before serialization occurs
        before.ensureLoaded(getContext());

        // serialize and deserialize
        final Account after = Account.fromJson(before.toJson());

        assertEquals(before.getDisplayName(), after.getDisplayName());
        assertEquals(before.getEmailAddress(), after.getEmailAddress());
        assertEquals(before.getSyncLookback(), after.getSyncLookback());
        assertEquals(before.getSyncInterval(), after.getSyncInterval());
        assertEquals(before.mHostAuthSend, after.mHostAuthSend);
        assertEquals(before.mHostAuthKeySend, after.mHostAuthKeySend);
        assertEquals(before.mHostAuthKeyRecv, after.mHostAuthKeyRecv);
        assertEquals(before.getFlags(), after.getFlags());
        assertEquals(before.getSenderName(), after.getSenderName());
        assertEquals(before.mProtocolVersion, after.mProtocolVersion);
        assertEquals(before.getSignature(), after.getSignature());
        assertEquals(before.mPingDuration, after.mPingDuration);

        assertNull(after.mSyncKey); // sync key is not serialized; field defaults to null
        assertEquals(0, after.mPolicyKey); // policy key is not serialized; field defaults to 0
    }
