    public static InputStream loadResource(final String resource) throws FileNotFoundException {
        final boolean hasLeadingSlash = resource.startsWith("/");
        final String stripped = hasLeadingSlash ? resource.substring(1) : resource;

        InputStream stream = null;

        final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader != null) {
            stream = classLoader.getResourceAsStream(resource);
            if (stream == null && hasLeadingSlash) {
                stream = classLoader.getResourceAsStream(stripped);
            }
        }

        if (stream == null) {
            throw new FileNotFoundException("resource " + resource + " not found");
        }

        return stream;
    }
