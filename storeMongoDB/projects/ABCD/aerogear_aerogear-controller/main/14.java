    public Cors setAllowCredentials(final HttpServletResponse response) {
        if (corsConfig.allowCookies()) {
            response.setHeader(ResponseHeader.ALLOW_CREDENTIALS.toString(), Boolean.TRUE.toString());
        }
        return this;
    }
