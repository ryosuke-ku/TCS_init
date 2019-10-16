    public void testBuildUponOpaqueStringUri() {
        Uri u = Uri.parse("bob:lee").buildUpon().scheme("robert").build();
        assertEquals("robert", u.getScheme());
        assertEquals("lee", u.getEncodedSchemeSpecificPart());
        assertEquals("lee", u.getSchemeSpecificPart());
        assertNull(u.getQuery());
        assertNull(u.getPath());
        assertNull(u.getAuthority());
        assertNull(u.getHost());
    }
