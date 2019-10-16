    public Cors setAllowMethods(final HttpServletResponse response) {
        final Set<String> httpMethods = corsConfig.getValidRequestMethods();
        if (httpMethods != null) {
            response.setHeader(ResponseHeader.ALLOW_METHODS.toString(), asString(httpMethods));
        }
        return this;
    }
