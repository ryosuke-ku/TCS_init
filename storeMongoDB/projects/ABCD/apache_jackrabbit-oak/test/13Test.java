    public void testSyncByIdUsingExceptionId() throws Exception {
        Group gr = userManager.createGroup(TestIdentityProvider.ID_EXCEPTION);
        setExternalID(gr, idp.getName());

        syncCtx.sync(TestIdentityProvider.ID_EXCEPTION);
    }
