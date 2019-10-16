    String skipLastSlash(String uri) {
        if (!uri.endsWith("/")) {
            return uri;
        }
        return uri.substring(0, uri.lastIndexOf("/"));
    }
