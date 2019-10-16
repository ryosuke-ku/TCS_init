    public void testParcelling() {
        parcelAndUnparcel(Uri.parse("foo:bob%20lee"));
        parcelAndUnparcel(Uri.fromParts("foo", "bob lee", "fragment"));
        parcelAndUnparcel(new Uri.Builder()
            .scheme("http")
            .authority("crazybob.org")
            .path("/rss/")
            .encodedQuery("a=b")
            .fragment("foo")
            .build());
    }
