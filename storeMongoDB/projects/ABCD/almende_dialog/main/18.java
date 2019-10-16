    public void setAccounts(Collection<String> accounts) {

        this.accounts = accounts;
        //make the first shared account as the owner (if its missing)
        if (accounts != null && !accounts.isEmpty() && (owner == null || owner.trim().isEmpty())) {
            owner = accounts.iterator().next();
        }
    }
