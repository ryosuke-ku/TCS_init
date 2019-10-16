    public void testPathSegmentDecoding() {
        Uri uri = Uri.parse("foo://bar/a%20a/b%20b");
        assertEquals("a a", uri.getPathSegments().get(0));
        assertEquals("b b", uri.getPathSegments().get(1));
    }
