    public static Map<String, String> parseParamters(URI uri) throws URISyntaxException {
        return uri.getQuery() == null ? emptyMap() : parseQuery(stripPrefix(uri.getQuery(), "?"));
    }
