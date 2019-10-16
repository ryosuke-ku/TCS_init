    public void testAddInvalidPrincipalsIgnore() throws Exception {
        CugPolicy cug = new CugPolicyImpl(path, NamePathMapper.DEFAULT, principalManager, ImportBehavior.IGNORE, principals);
        assertTrue(cug.addPrincipals(
                new PrincipalImpl("unknown"),
                EveryonePrincipal.getInstance()));

        Set<Principal> principalSet = cug.getPrincipals();
        assertEquals(2, principalSet.size());
        assertFalse(principalSet.contains(new PrincipalImpl("unknown")));
        assertFalse(principalSet.contains(new PrincipalImpl("")));
    }
