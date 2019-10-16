    public void defaultResourceBundleIsFetched() throws Exception {
        final Map<String, String> loadResourceAndDefault = ConfigurationLoader.loadResourceAndDefault("not-existing");
        assertNotNull(loadResourceAndDefault);
        assertEquals("needle-hsql-hibernate.cfg.xml", loadResourceAndDefault.get(HIBERNATE_CFG_FILENAME_KEY));
    }
