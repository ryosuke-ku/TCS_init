    public boolean removePrincipals(@Nonnull Principal... principals) {
        boolean modified = false;
        for (Principal principal : principals) {
            if (principal != null) {
                modified |= this.principals.remove(principal);
            }
        }
        return modified;
    }
