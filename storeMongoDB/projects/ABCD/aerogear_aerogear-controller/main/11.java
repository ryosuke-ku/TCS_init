    public Cors setOrigin(final HttpServletResponse response, final String origin) {
        if (hasOriginHeader()) {
            response.setHeader(ResponseHeader.ALLOW_ORIGIN.toString(), origin);
        }
        return this;
    }
