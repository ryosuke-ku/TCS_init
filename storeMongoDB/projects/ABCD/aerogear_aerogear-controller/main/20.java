    public Cors setAllowHeaders(final HttpServletResponse response) {
        response.setHeader(ResponseHeader.ALLOW_HEADERS.toString(), asString(corsConfig.getValidRequestHeaders()));
        return this;
    }
