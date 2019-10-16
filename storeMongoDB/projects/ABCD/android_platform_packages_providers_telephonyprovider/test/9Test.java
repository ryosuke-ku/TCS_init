    public void testRestoreMms_AllMms() throws Exception {
        JsonReader jsonReader = new JsonReader(new StringReader(addRandomDataToJson(mAllMmsJson)));
        FakeMmsProvider mmsProvider = new FakeMmsProvider(mMmsAllContentValues);
        mMockContentResolver.addProvider("mms", mmsProvider);
        mTelephonyBackupAgent.putMmsMessagesToProvider(jsonReader);
        assertEquals(18, mmsProvider.getRowsAdded());
        assertEquals(mThreadProvider.mIsThreadArchived, mThreadProvider.mUpdateThreadsArchived);
    }
