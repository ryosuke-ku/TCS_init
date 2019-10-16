    boolean haveAccountsChanged(Account[] currentSystemAccounts) {
        final ContactsDatabaseHelper dbHelper = mDbHelper.get();
        final Set<Account> knownAccountSet;
        try {
            knownAccountSet = stringToAccounts(
                    dbHelper.getProperty(DbProperties.KNOWN_ACCOUNTS, ""));
        } catch (IllegalArgumentException e) {
            // Failed to get the last known accounts for an unknown reason.  Let's just
            // treat as if accounts have changed.
            return true;
        }
        final Set<Account> currentAccounts = Sets.newHashSet(currentSystemAccounts);
        return !knownAccountSet.equals(currentAccounts);
    }
