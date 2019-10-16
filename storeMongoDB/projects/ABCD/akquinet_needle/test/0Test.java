    public void testGetProperty() throws Exception {
        assertEquals("TestDataModel", configurationLoader.getProperty(PERSISTENCEUNIT_NAME_KEY));
        assertNull(configurationLoader.getProperty("any key"));
    }
