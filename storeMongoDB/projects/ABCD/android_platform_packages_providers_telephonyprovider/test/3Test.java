    public void testBackup_WithQuotaExceeded() throws Exception {
        mTelephonyBackupAgent.mMaxMsgPerFile = 1;
        final int backupSize = 7168;
        final int backupSizeAfterFirstQuotaHit = 6144;
        final int backupSizeAfterSecondQuotaHit = 5120;

        mSmsTable.addAll(Arrays.asList(mSmsRows));
        mMmsTable.addAll(Arrays.asList(mMmsRows));

        FullBackupDataOutput fullBackupDataOutput = new FullBackupDataOutput();
        mTelephonyBackupAgent.onFullBackup(fullBackupDataOutput);
        assertEquals(backupSize, fullBackupDataOutput.getSize());

        mTelephonyBackupAgent.onQuotaExceeded(backupSize, backupSize - 100);
        fullBackupDataOutput = new FullBackupDataOutput();
        mTelephonyBackupAgent.onFullBackup(fullBackupDataOutput);
        assertEquals(backupSizeAfterFirstQuotaHit, fullBackupDataOutput.getSize());

        mTelephonyBackupAgent.onQuotaExceeded(backupSizeAfterFirstQuotaHit,
                backupSizeAfterFirstQuotaHit - 200);
        fullBackupDataOutput = new FullBackupDataOutput();
        mTelephonyBackupAgent.onFullBackup(fullBackupDataOutput);
        assertEquals(backupSizeAfterSecondQuotaHit, fullBackupDataOutput.getSize());
    }
