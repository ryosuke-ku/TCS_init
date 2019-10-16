    public void testRemovePrincipals() throws Exception {
        CugPolicy cug = new CugPolicyImpl(path, NamePathMapper.DEFAULT, principalManager,
                ImportBehavior.BESTEFFORT,
                ImmutableSet.of(testPrincipal, EveryonePrincipal.getInstance()));

        assertFalse(cug.removePrincipals(new PrincipalImpl("unknown")));
        assertTrue(cug.removePrincipals(testPrincipal, EveryonePrincipal.getInstance(), new PrincipalImpl("unknown")));
        assertTrue(cug.getPrincipals().isEmpty());
    }
