    static Set<Account> stringToAccounts(String accountsString) {
        final Set<Account> ret = Sets.newHashSet();
        if (accountsString.length() == 0) return ret; // no accounts
        try {
            for (String accountString : accountsString.split(ACCOUNT_STRING_SEPARATOR_OUTER)) {
                String[] nameAndType = accountString.split(ACCOUNT_STRING_SEPARATOR_INNER);
                ret.add(new Account(nameAndType[0], nameAndType[1]));
            }
            return ret;
        } catch (RuntimeException ex) {
            throw new IllegalArgumentException("Malformed string", ex);
        }
    }
