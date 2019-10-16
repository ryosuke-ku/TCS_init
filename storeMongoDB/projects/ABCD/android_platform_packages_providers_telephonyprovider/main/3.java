    public void onFullBackup(FullBackupDataOutput data) throws IOException {
        SharedPreferences sharedPreferences = getSharedPreferences(BACKUP_PREFS, MODE_PRIVATE);
        if (sharedPreferences.getLong(QUOTA_RESET_TIME, Long.MAX_VALUE) <
                System.currentTimeMillis()) {
            clearSharedPreferences();
        }

        mBytesOverQuota = sharedPreferences.getLong(BACKUP_DATA_BYTES, 0) -
                sharedPreferences.getLong(QUOTA_BYTES, Long.MAX_VALUE);
        if (mBytesOverQuota > 0) {
            mBytesOverQuota *= BYTES_OVER_QUOTA_MULTIPLIER;
        }

        try (
                Cursor smsCursor = mContentResolver.query(Telephony.Sms.CONTENT_URI, SMS_PROJECTION,
                        null, null, ORDER_BY_DATE);
                // Do not backup non text-only MMS's.
                Cursor mmsCursor = mContentResolver.query(Telephony.Mms.CONTENT_URI, MMS_PROJECTION,
                        Telephony.Mms.TEXT_ONLY+"=1", null, ORDER_BY_DATE)) {

            if (smsCursor != null) {
                smsCursor.moveToFirst();
            }
            if (mmsCursor != null) {
                mmsCursor.moveToFirst();
            }

            // It backs up messages from the oldest to newest. First it looks at the timestamp of
            // the next SMS messages and MMS message. If the SMS is older it backs up 1000 SMS
            // messages, otherwise 1000 MMS messages. Repeat until out of SMS's or MMS's.
            // It ensures backups are incremental.
            int fileNum = 0;
            while (smsCursor != null && !smsCursor.isAfterLast() &&
                    mmsCursor != null && !mmsCursor.isAfterLast()) {
                final long smsDate = TimeUnit.MILLISECONDS.toSeconds(getMessageDate(smsCursor));
                final long mmsDate = getMessageDate(mmsCursor);
                if (smsDate < mmsDate) {
                    backupAll(data, smsCursor,
                            String.format(Locale.US, SMS_BACKUP_FILE_FORMAT, fileNum++));
                } else {
                    backupAll(data, mmsCursor, String.format(Locale.US,
                            MMS_BACKUP_FILE_FORMAT, fileNum++));
                }
            }

            while (smsCursor != null && !smsCursor.isAfterLast()) {
                backupAll(data, smsCursor,
                        String.format(Locale.US, SMS_BACKUP_FILE_FORMAT, fileNum++));
            }

            while (mmsCursor != null && !mmsCursor.isAfterLast()) {
                backupAll(data, mmsCursor,
                        String.format(Locale.US, MMS_BACKUP_FILE_FORMAT, fileNum++));
            }
        }

        mThreadArchived = new HashMap<>();
    }
