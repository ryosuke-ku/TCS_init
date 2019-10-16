    public void testAccountsToString() {
        final Set<Account> EXPECTED_0 = Sets.newHashSet();
        final Set<Account> EXPECTED_1 = Sets.newHashSet(ACCOUNT_1);
        final Set<Account> EXPECTED_2 = Sets.newHashSet(ACCOUNT_2);
        final Set<Account> EXPECTED_1_2 = Sets.newHashSet(ACCOUNT_1, ACCOUNT_2);

        final Set<Account> ACTUAL_0 = Sets.newHashSet();
        final Set<Account> ACTUAL_1 = Sets.newHashSet(ACCOUNT_1);
        final Set<Account> ACTUAL_2 = Sets.newHashSet(ACCOUNT_2);
        final Set<Account> ACTUAL_1_2 = Sets.newHashSet(ACCOUNT_2, ACCOUNT_1);

        assertTrue(EXPECTED_0.equals(accountsToStringToAccounts(ACTUAL_0)));
        assertFalse(EXPECTED_0.equals(accountsToStringToAccounts(ACTUAL_1)));
        assertFalse(EXPECTED_0.equals(accountsToStringToAccounts(ACTUAL_2)));
        assertFalse(EXPECTED_0.equals(accountsToStringToAccounts(ACTUAL_1_2)));

        assertFalse(EXPECTED_1.equals(accountsToStringToAccounts(ACTUAL_0)));
        assertTrue(EXPECTED_1.equals(accountsToStringToAccounts(ACTUAL_1)));
        assertFalse(EXPECTED_1.equals(accountsToStringToAccounts(ACTUAL_2)));
        assertFalse(EXPECTED_1.equals(accountsToStringToAccounts(ACTUAL_1_2)));

        assertFalse(EXPECTED_2.equals(accountsToStringToAccounts(ACTUAL_0)));
        assertFalse(EXPECTED_2.equals(accountsToStringToAccounts(ACTUAL_1)));
        assertTrue(EXPECTED_2.equals(accountsToStringToAccounts(ACTUAL_2)));
        assertFalse(EXPECTED_2.equals(accountsToStringToAccounts(ACTUAL_1_2)));

        assertFalse(EXPECTED_1_2.equals(accountsToStringToAccounts(ACTUAL_0)));
        assertFalse(EXPECTED_1_2.equals(accountsToStringToAccounts(ACTUAL_1)));
        assertFalse(EXPECTED_1_2.equals(accountsToStringToAccounts(ACTUAL_2)));
        assertTrue(EXPECTED_1_2.equals(accountsToStringToAccounts(ACTUAL_1_2)));

        try {
            ContactsProvider2.stringToAccounts("x");
            fail("Didn't throw for malformed input");
        } catch (IllegalArgumentException expected) {
        }
    }
