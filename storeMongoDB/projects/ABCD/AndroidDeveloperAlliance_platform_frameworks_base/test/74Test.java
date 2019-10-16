    public void testAuthorityParsing() {
        Uri uri = Uri.parse("http://localhost:42");
        assertEquals("localhost", uri.getHost());
        assertEquals(42, uri.getPort());

        uri = Uri.parse("http://bob@localhost:42");
        assertEquals("bob", uri.getUserInfo());
        assertEquals("localhost", uri.getHost());
        assertEquals(42, uri.getPort());

        uri = Uri.parse("http://bob%20lee@localhost:42");
        assertEquals("bob lee", uri.getUserInfo());
        assertEquals("bob%20lee", uri.getEncodedUserInfo());

        uri = Uri.parse("http://bob%40lee%3ajr@local%68ost:4%32");
        assertEquals("bob@lee:jr", uri.getUserInfo());
        assertEquals("localhost", uri.getHost());
        assertEquals(42, uri.getPort());

        uri = Uri.parse("http://localhost");
        assertEquals("localhost", uri.getHost());
        assertEquals(-1, uri.getPort());
    }
