    public void uriCreation() {

        URI base = URI.create("http://foo.bar/baz/");
        assertEquals(URIUtil.createAbsoluteURI(base, "/asdf.txt").toString(),
                     "http://foo.bar/asdf.txt"
        );

        base = URI.create("http://foo.bar/baz");
        assertEquals(URIUtil.createAbsoluteURI(base, "/asdf.txt").toString(),
                     "http://foo.bar/asdf.txt"
        );

        base = URI.create("http://foo.bar/baz/");
        assertEquals(URIUtil.createAbsoluteURI(base, "asdf.txt").toString(),
                     "http://foo.bar/baz/asdf.txt"
        );

        base = URI.create("http://foo.bar/baz");
        assertEquals(URIUtil.createAbsoluteURI(base, "asdf.txt").toString(),
                     "http://foo.bar/asdf.txt"
        );
    }
