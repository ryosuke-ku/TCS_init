    public void testHaveAccountsChanged() {
        final ContactsProvider2 cp = (ContactsProvider2) getProvider();

        final Account[] ACCOUNTS_0 = new Account[] {};
        final Account[] ACCOUNTS_1 = new Account[] {ACCOUNT_1};
        final Account[] ACCOUNTS_2 = new Account[] {ACCOUNT_2};
        final Account[] ACCOUNTS_1_2 = new Account[] {ACCOUNT_1, ACCOUNT_2};
        final Account[] ACCOUNTS_2_1 = new Account[] {ACCOUNT_2, ACCOUNT_1};

        // Add ACCOUNT_1

        assertTrue(cp.haveAccountsChanged(ACCOUNTS_1));
        cp.saveAccounts(ACCOUNTS_1);
        assertFalse(cp.haveAccountsChanged(ACCOUNTS_1));

        // Add ACCOUNT_2

        assertTrue(cp.haveAccountsChanged(ACCOUNTS_1_2));
        // (try with reverse order)
        assertTrue(cp.haveAccountsChanged(ACCOUNTS_2_1));
        cp.saveAccounts(ACCOUNTS_1_2);
        assertFalse(cp.haveAccountsChanged(ACCOUNTS_1_2));
        // (try with reverse order)
        assertFalse(cp.haveAccountsChanged(ACCOUNTS_2_1));

        // Remove ACCOUNT_1

        assertTrue(cp.haveAccountsChanged(ACCOUNTS_2));
        cp.saveAccounts(ACCOUNTS_2);
        assertFalse(cp.haveAccountsChanged(ACCOUNTS_2));

        // Remove ACCOUNT_2

        assertTrue(cp.haveAccountsChanged(ACCOUNTS_0));
        cp.saveAccounts(ACCOUNTS_0);
        assertFalse(cp.haveAccountsChanged(ACCOUNTS_0));

        // Test with malformed DB property.

        final ContactsDatabaseHelper dbHelper = cp.getThreadActiveDatabaseHelperForTest();
        dbHelper.setProperty(DbProperties.KNOWN_ACCOUNTS, "x");

        // With malformed property the method always return true.
        assertTrue(cp.haveAccountsChanged(ACCOUNTS_0));
        assertTrue(cp.haveAccountsChanged(ACCOUNTS_1));
    }
