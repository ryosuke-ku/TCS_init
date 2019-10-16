    public void testGetQueryParameter() {
        String nestedUrl = "http://crazybob.org/?a=1&b=2";
        Uri uri = Uri.parse("http://test/").buildUpon()
                .appendQueryParameter("foo", "bar")
                .appendQueryParameter("nested", nestedUrl).build();
        assertEquals(nestedUrl, uri.getQueryParameter("nested"));
        assertEquals(nestedUrl, uri.getQueryParameters("nested").get(0));
    }
