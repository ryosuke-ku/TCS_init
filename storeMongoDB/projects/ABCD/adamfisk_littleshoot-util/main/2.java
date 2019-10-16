    public static String stripHost(final String uri)
        {
        if (!uri.startsWith("http")) 
            {
            // It's likely a URI path, not the full URI (i.e. the host is 
            // already stripped).
            return uri;
            }
        final String noHttpUri = StringUtils.substringAfter(uri, "://");
        final int slashIndex = noHttpUri.indexOf("/");
        if (slashIndex == -1)
            {
            return "/";
            }
        final String noHostUri = noHttpUri.substring(slashIndex);
        return noHostUri;
        }
