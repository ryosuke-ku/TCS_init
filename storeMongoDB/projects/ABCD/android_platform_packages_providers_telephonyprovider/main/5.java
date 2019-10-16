    public void onQuotaExceeded(long backupDataBytes, long quotaBytes) {
        SharedPreferences sharedPreferences = getSharedPreferences(BACKUP_PREFS, MODE_PRIVATE);
        if (sharedPreferences.contains(BACKUP_DATA_BYTES)
                && sharedPreferences.contains(QUOTA_BYTES)) {
            // Increase backup size by the size we skipped during previous backup.
            backupDataBytes += (sharedPreferences.getLong(BACKUP_DATA_BYTES, 0)
                    - sharedPreferences.getLong(QUOTA_BYTES, 0)) * BYTES_OVER_QUOTA_MULTIPLIER;
        }
        sharedPreferences.edit()
                .putLong(BACKUP_DATA_BYTES, backupDataBytes)
                .putLong(QUOTA_BYTES, quotaBytes)
                .putLong(QUOTA_RESET_TIME, System.currentTimeMillis() + QUOTA_RESET_INTERVAL)
                .apply();
    }
