    public void testEqualsAndHashCode() {

        Uri a = Uri.parse("http://crazybob.org/test/?foo=bar#tee");

        Uri b = new Uri.Builder()
                .scheme("http")
                .authority("crazybob.org")
                .path("/test/")
                .encodedQuery("foo=bar")
                .fragment("tee")
                .build();

        // Try alternate builder methods.
        Uri c = new Uri.Builder()
                .scheme("http")
                .encodedAuthority("crazybob.org")
                .encodedPath("/test/")
                .encodedQuery("foo=bar")
                .encodedFragment("tee")
                .build();

        assertFalse(Uri.EMPTY.equals(null));

        assertEquals(a, b);
        assertEquals(b, c);
        assertEquals(c, a);

        assertEquals(a.hashCode(), b.hashCode());
        assertEquals(b.hashCode(), c.hashCode());
    }
