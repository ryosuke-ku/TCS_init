    public static URI removeQuery(URI uri) throws URISyntaxException {
        return createURIWithQuery(uri, null);
    }
