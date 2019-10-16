    public boolean isAllowed (Group group, Permission permission) {
        return rulesTable[group.ordinal()][permission.ordinal()] == 1;
    }
