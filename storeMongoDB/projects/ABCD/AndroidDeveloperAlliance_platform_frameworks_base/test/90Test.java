    private static void compareHierarchical(String uriString, String ssp,
            Uri uri,
            String scheme, String authority, String path, String query,
            String fragment) {
        assertEquals(scheme, uri.getScheme());
        assertEquals(authority, uri.getAuthority());
        assertEquals(authority, uri.getEncodedAuthority());
        assertEquals(path, uri.getPath());
        assertEquals(path, uri.getEncodedPath());
        assertEquals(query, uri.getQuery());
        assertEquals(query, uri.getEncodedQuery());
        assertEquals(fragment, uri.getFragment());
        assertEquals(fragment, uri.getEncodedFragment());
        assertEquals(ssp, uri.getSchemeSpecificPart());

        if (scheme != null) {
            assertTrue(uri.isAbsolute());
            assertFalse(uri.isRelative());
        } else {
            assertFalse(uri.isAbsolute());
            assertTrue(uri.isRelative());
        }

        assertFalse(uri.isOpaque());
        assertTrue(uri.isHierarchical());

        assertEquals(uriString, uri.toString());
    }
