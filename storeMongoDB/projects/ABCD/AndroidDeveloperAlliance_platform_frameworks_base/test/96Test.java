    private void testOpaqueUri(Uri uri) {
        assertEquals("mailto", uri.getScheme());
        assertEquals("nobody", uri.getSchemeSpecificPart());
        assertEquals("nobody", uri.getEncodedSchemeSpecificPart());

        assertNull(uri.getFragment());
        assertTrue(uri.isAbsolute());
        assertTrue(uri.isOpaque());
        assertFalse(uri.isRelative());
        assertFalse(uri.isHierarchical());

        assertNull(uri.getAuthority());
        assertNull(uri.getEncodedAuthority());
        assertNull(uri.getPath());
        assertNull(uri.getEncodedPath());
        assertNull(uri.getUserInfo());
        assertNull(uri.getEncodedUserInfo());
        assertNull(uri.getQuery());
        assertNull(uri.getEncodedQuery());
        assertNull(uri.getHost());
        assertEquals(-1, uri.getPort());

        assertTrue(uri.getPathSegments().isEmpty());
        assertNull(uri.getLastPathSegment());

        assertEquals("mailto:nobody", uri.toString());

        Uri withFragment = uri.buildUpon().fragment("top").build();
        assertEquals("mailto:nobody#top", withFragment.toString());
    }
