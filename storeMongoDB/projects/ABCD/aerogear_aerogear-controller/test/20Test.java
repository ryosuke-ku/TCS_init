    public void allowHeaders() {
        CorsTestUtil.setValidRequestHeaders(corsConfig, "HEADER1", "HEADER2");
        cors.setAllowHeaders(response);
        verify(response).setHeader(Cors.ResponseHeader.ALLOW_HEADERS.toString(), "HEADER1,HEADER2");
    }
