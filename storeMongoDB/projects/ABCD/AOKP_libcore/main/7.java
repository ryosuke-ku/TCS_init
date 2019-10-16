    public Set<String> allSystemAliases() {
        Set<String> result = new HashSet<String>();
        String[] files = systemDir.list();
        if (files == null) {
            return result;
        }
        for (String filename : files) {
            String alias = PREFIX_SYSTEM + filename;
            if (containsAlias(alias, true)) {
                result.add(alias);
            }
        }
        return result;
    }
