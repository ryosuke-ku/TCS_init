    private boolean containsAlias(String alias, boolean includeDeletedSystem) {
        return getCertificate(alias, includeDeletedSystem) != null;
    }
