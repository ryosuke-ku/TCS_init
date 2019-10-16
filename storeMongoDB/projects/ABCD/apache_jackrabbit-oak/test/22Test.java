    public void testIsSameIDPSyncedGroup() throws Exception {
        ExternalIdentity externalGroup = idp.listGroups().next();
        sync(externalGroup);

        assertTrue(syncCtx.isSameIDP(userManager.getAuthorizable(externalGroup.getId())));
    }
