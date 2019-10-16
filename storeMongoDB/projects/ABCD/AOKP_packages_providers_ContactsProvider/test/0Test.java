    private void assertUriTypeMatch(UriType expectedType, String uri) {
        assertEquals(expectedType, mTypedUriMatcherImpl.match(Uri.parse(uri)));
    }
