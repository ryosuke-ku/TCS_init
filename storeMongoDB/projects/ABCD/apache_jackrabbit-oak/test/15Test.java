    public void testApplyMembership() throws Exception {
        User u = getTestUser();
        Group gr = createTestGroup();

        syncCtx.applyMembership(u, ImmutableSet.of(gr.getID()));
        assertTrue(gr.isDeclaredMember(u));
        assertTrue(root.hasPendingChanges());
    }
