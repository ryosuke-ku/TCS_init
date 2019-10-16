    public void testBackupMms_WithExactFileLimit() throws Exception {
        mMmsTable.addAll(Arrays.asList(mMmsRows));
        mTelephonyBackupAgent.mMaxMsgPerFile = 3;
        mTelephonyBackupAgent.putMmsMessagesToJson(mMmsCursor, new JsonWriter(mStringWriter));
        assertEquals(mAllMmsJson, mStringWriter.toString());
    }
