    public void testGetPrincipalNames() {
        CugPolicyImpl cug = createCugPolicy(principals);

        Iterator<String> it = cug.getPrincipalNames().iterator();
        assertTrue(it.hasNext());
        assertEquals("test", it.next());
        assertFalse(it.hasNext());
    }
