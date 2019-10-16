    public void testBuildUponEncodedOpaqueUri() {
        Uri a = new Uri.Builder()
                .scheme("foo")
                .encodedOpaquePart("bar")
                .fragment("tee")
                .build();
        Uri b = a.buildUpon().fragment("new").build();
        assertEquals("new", b.getFragment());
        assertEquals("bar", b.getSchemeSpecificPart());
        assertEquals("foo", b.getScheme());
    }
