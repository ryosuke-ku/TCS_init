    public boolean isDenied (Group group, Permission permission) {
        return rulesTable[group.ordinal()][permission.ordinal()] == 0;
    }
