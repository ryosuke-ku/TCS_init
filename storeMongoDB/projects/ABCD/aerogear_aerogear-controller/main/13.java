    public Cors setAnyOrigin(final HttpServletResponse response) {
        return setOrigin(response, "*");
    }
