    public Cors setMaxAge(final HttpServletResponse response) {
        if (corsConfig.hasMaxAge()) {
            response.setHeader(ResponseHeader.MAX_AGE.toString(), Long.toString(corsConfig.getMaxAge()));
        }
        return this;
    }
