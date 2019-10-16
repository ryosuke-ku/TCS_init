    public void testBackupMms_AllMms() throws Exception {
        mTelephonyBackupAgent.mMaxMsgPerFile = 4;
        mMmsTable.addAll(Arrays.asList(mMmsRows));
        mTelephonyBackupAgent.putMmsMessagesToJson(mMmsCursor, new JsonWriter(mStringWriter));
        assertEquals(mAllMmsJson, mStringWriter.toString());
    }
