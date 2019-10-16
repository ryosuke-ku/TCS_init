    void saveAccounts(Account[] systemAccounts) {
        final ContactsDatabaseHelper dbHelper = mDbHelper.get();
        dbHelper.setProperty(
                DbProperties.KNOWN_ACCOUNTS, accountsToString(Sets.newHashSet(systemAccounts)));
    }
