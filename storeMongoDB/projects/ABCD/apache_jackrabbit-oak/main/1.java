    public boolean addPrincipals(@Nonnull Principal... principals) throws AccessControlException {
        boolean modified = false;
        for (Principal principal : principals) {
            if (isValidPrincipal(principal)) {
                modified |= this.principals.add(principal);
            }
        }
        return modified;
    }
