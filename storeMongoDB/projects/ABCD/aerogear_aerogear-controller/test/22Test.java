    public void buildOrdering() {
        CorsConfig.enableCorsSupport().echoOrigin().enableCookies().maxAge(10l).validRequestMethods(RequestMethod.GET)
                .validRequestHeaders("");
    }
