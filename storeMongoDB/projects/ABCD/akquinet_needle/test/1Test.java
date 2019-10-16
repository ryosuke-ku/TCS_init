    public void testContainsKey() throws Exception {
        assertFalse(configurationLoader.containsKey("anykey"));
        assertTrue(configurationLoader.containsKey(PERSISTENCEUNIT_NAME_KEY));
    }
