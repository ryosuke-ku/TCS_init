    public Cors setEchoOrigin(final HttpServletResponse response) {
        return setOrigin(response, request.getHeader(RequestHeader.ORIGIN.headerName));
    }
