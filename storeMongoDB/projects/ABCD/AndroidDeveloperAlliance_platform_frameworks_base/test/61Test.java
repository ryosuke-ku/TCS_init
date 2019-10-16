    public void testPathOperations() {
        Uri uri = Uri.parse("content://user/a/b");

        assertEquals(2, uri.getPathSegments().size());
        assertEquals("b", uri.getLastPathSegment());

        Uri first = uri;
        uri = uri.buildUpon().appendPath("c").build();

        assertEquals(3, uri.getPathSegments().size());
        assertEquals("c", uri.getLastPathSegment());
        assertEquals("content://user/a/b/c", uri.toString());

        uri = ContentUris.withAppendedId(uri, 100);

        assertEquals(4, uri.getPathSegments().size());
        assertEquals("100", uri.getLastPathSegment());
        assertEquals(100, ContentUris.parseId(uri));
        assertEquals("content://user/a/b/c/100", uri.toString());

        // Make sure the original URI is still intact.
        assertEquals(2, first.getPathSegments().size());
        assertEquals("b", first.getLastPathSegment());

        try {
            first.getPathSegments().get(2);
            fail();
        } catch (IndexOutOfBoundsException e) {}

        assertEquals(null, Uri.EMPTY.getLastPathSegment());

        Uri withC = Uri.parse("foo:/a/b/").buildUpon().appendPath("c").build();
        assertEquals("/a/b/c", withC.getPath());
    }
