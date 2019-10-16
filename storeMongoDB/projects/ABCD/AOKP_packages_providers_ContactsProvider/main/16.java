    static String accountsToString(Set<Account> accounts) {
        final StringBuilder sb = new StringBuilder();
        for (Account account : accounts) {
            if (sb.length() > 0) {
                sb.append(ACCOUNT_STRING_SEPARATOR_OUTER);
            }
            sb.append(account.name);
            sb.append(ACCOUNT_STRING_SEPARATOR_INNER);
            sb.append(account.type);
        }
        return sb.toString();
    }
