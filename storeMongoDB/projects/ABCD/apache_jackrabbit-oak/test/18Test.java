    public void testIsExpiredLocalGroup() throws Exception {
        Group gr = createTestGroup();
        assertTrue(syncCtx.isExpired(gr, syncConfig.group().getExpirationTime(), "any"));
    }
