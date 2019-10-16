    public void testGetQueryParameterWorkaround() {
        // This was a workaround for a bug where getQueryParameter called
        // getQuery() instead of getEncodedQuery().
        String nestedUrl = "http://crazybob.org/?a=1&b=2";
        Uri uri = Uri.parse("http://test/").buildUpon()
                .appendQueryParameter("foo", "bar")
                .appendQueryParameter("nested", Uri.encode(nestedUrl)).build();
        assertEquals(nestedUrl, Uri.decode(uri.getQueryParameter("nested")));
        assertEquals(nestedUrl,
                Uri.decode(uri.getQueryParameters("nested").get(0)));
    }
