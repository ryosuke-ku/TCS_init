    public void testBackupSms_AllSms() throws Exception {
        mTelephonyBackupAgent.mMaxMsgPerFile = 4;
        mSmsTable.addAll(Arrays.asList(mSmsRows));
        mTelephonyBackupAgent.putSmsMessagesToJson(mSmsCursor, new JsonWriter(mStringWriter));
        assertEquals(mAllSmsJson, mStringWriter.toString());
    }
