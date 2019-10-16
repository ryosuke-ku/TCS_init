    public void urlCreation() throws Exception {

        URL baseURL = new URL("http://foo.bar:0");
        assertEquals(
                URIUtil.createAbsoluteURL(baseURL, "asdf.txt").toString(),
                "http://foo.bar:0/asdf.txt"
        );

        baseURL = new URL("http://foo.bar:0/");
        assertEquals(
                URIUtil.createAbsoluteURL(baseURL, "asdf.txt").toString(),
                "http://foo.bar:0/asdf.txt"
        );

        baseURL = new URL("http://foo.bar");
        assertEquals(
                URIUtil.createAbsoluteURL(baseURL, "asdf.txt").toString(),
                "http://foo.bar/asdf.txt"
        );

        baseURL = new URL("http://foo.bar");
        assertEquals(
                URIUtil.createAbsoluteURL(baseURL, "/asdf.txt").toString(),
                "http://foo.bar/asdf.txt"
        );

        baseURL = new URL("http://foo.bar:0");
        assertEquals(
                URIUtil.createAbsoluteURL(baseURL, "asdf.txt").toString(),
                "http://foo.bar:0/asdf.txt"
        );

        baseURL = new URL("http://foo.bar:0/");
        assertEquals(
                URIUtil.createAbsoluteURL(baseURL, "asdf.txt").toString(),
                "http://foo.bar:0/asdf.txt"
        );

        baseURL = new URL("http://foo.bar");
        assertEquals(
                URIUtil.createAbsoluteURL(baseURL, "asdf.txt").toString(),
                "http://foo.bar/asdf.txt"
        );

        baseURL = new URL("http://foo.bar");
        assertEquals(
                URIUtil.createAbsoluteURL(baseURL, "/asdf.txt").toString(),
                "http://foo.bar/asdf.txt"
        );

        baseURL = new URL("http://foo.bar/baz");
        assertEquals(
                URIUtil.createAbsoluteURL(baseURL, "/asdf.txt").toString(),
                "http://foo.bar/asdf.txt"
        );

        baseURL = new URL("http://foo.bar/baz");
        assertEquals(
                URIUtil.createAbsoluteURL(baseURL, "asdf.txt").toString(),
                "http://foo.bar/asdf.txt"
        );

        baseURL = new URL("http://foo.bar/baz/");
        assertEquals(
                URIUtil.createAbsoluteURL(baseURL, "asdf.txt").toString(),
                "http://foo.bar/baz/asdf.txt"
        );
    }
