    public static DefaultApplicationProperties properties(Map<String, Object> properties) {
        Map<String, Object> defaults = Maps.newHashMap(TEMP_DIR, (Object) createTempDirPath(null));
        defaults.put(LOCALE, createDefaultClientLocale(null));
        defaults.put(PACKAGES, createUserDefinedPackageNames(null));
        defaults.putAll(properties);
        return new DefaultApplicationProperties(defaults);
    }
