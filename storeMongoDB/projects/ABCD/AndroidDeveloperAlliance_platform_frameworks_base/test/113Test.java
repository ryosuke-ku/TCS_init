    public void testClearQueryParameters() {
        Uri uri = Uri.parse("http://www.google.com/?a=x&b=y&c=z").buildUpon()
            .clearQuery().appendQueryParameter("foo", "bar").build();
        Set<String> names = uri.getQueryParameterNames();
        assertEquals(1, names.size());
        assertEquals("foo", names.iterator().next());
    }
