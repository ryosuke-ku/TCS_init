    public void testStringUri() {
        assertEquals("bob lee",
                Uri.parse("foo:bob%20lee").getSchemeSpecificPart());
        assertEquals("bob%20lee",
                Uri.parse("foo:bob%20lee").getEncodedSchemeSpecificPart());
        assertEquals("/bob%20lee",
                Uri.parse("foo:/bob%20lee").getEncodedPath());
        assertNull(Uri.parse("foo:bob%20lee").getPath());
        assertEquals("bob%20lee",
                Uri.parse("foo:?bob%20lee").getEncodedQuery());
        assertNull(Uri.parse("foo:bar#?bob%20lee").getQuery());
        assertEquals("bob%20lee",
                Uri.parse("foo:#bob%20lee").getEncodedFragment());
    }
