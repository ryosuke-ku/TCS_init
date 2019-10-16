    public void testEmptyRoles() {
        HierarchicalRoles roles = new HierarchicalRoles();
        HierarchicalRoles requested = new HierarchicalRoles("test-user");

        assertEquals(0, roles.getNodes().size());
        assertFalse(roles.hasAnyRole(requested));
        assertTrue(requested.hasAnyRole(roles));
    }
