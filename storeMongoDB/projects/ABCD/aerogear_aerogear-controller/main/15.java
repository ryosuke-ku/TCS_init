    public Cors setExposeHeaders(final HttpServletResponse response) {
        if (corsConfig.exposeHeaders()) {
            final List<String> headers = corsConfig.getExposeHeaders();
            if (headers != null) {
                response.setHeader(ResponseHeader.EXPOSE_HEADERS.toString(), asString(headers));
            }
        }
        return this;
    }
