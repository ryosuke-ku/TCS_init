    public void allowMethodsSet() {
        CorsTestUtil.setValidRequestMethods(corsConfig, "PUT", "GET", "POST");
        cors.setAllowMethods(response);
        verify(response).setHeader(Cors.ResponseHeader.ALLOW_METHODS.toString(), "PUT,GET,POST");
    }
