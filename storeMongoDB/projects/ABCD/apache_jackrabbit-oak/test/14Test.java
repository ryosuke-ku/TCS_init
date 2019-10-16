    public void testSyncMembershipDepth1() throws Exception {
        ExternalUser externalUser = idp.listUsers().next();
        Authorizable a = syncCtx.createUser(externalUser);

        syncCtx.syncMembership(externalUser, a, 1);
        assertTrue(root.hasPendingChanges());

        for (ExternalIdentityRef ref : externalUser.getDeclaredGroups()) {
            Group g = userManager.getAuthorizable(ref.getId(), Group.class);
            assertNotNull(g);
            assertTrue(g.isDeclaredMember(a));
        }
    }
