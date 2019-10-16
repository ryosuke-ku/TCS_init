    void clearSharedPreferences() {
        getSharedPreferences(BACKUP_PREFS, MODE_PRIVATE).edit()
                .remove(BACKUP_DATA_BYTES)
                .remove(QUOTA_BYTES)
                .remove(QUOTA_RESET_TIME)
                .apply();
    }
