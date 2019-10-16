    public void testRestoreSms_AllSms() throws Exception {
        mTelephonyBackupAgent.initUnknownSender();
        JsonReader jsonReader = new JsonReader(new StringReader(addRandomDataToJson(mAllSmsJson)));
        FakeSmsProvider smsProvider = new FakeSmsProvider(mSmsRows);
        mMockContentResolver.addProvider("sms", smsProvider);
        mTelephonyBackupAgent.putSmsMessagesToProvider(jsonReader);
        assertEquals(mSmsRows.length, smsProvider.getRowsAdded());
        assertEquals(mThreadProvider.mIsThreadArchived, mThreadProvider.mUpdateThreadsArchived);
    }
