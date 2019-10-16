    public void exposeHeaders() {
        CorsTestUtil.setExposeHeaders(corsConfig, "Header1", "Header2");
        cors.setExposeHeaders(response);
        verify(response).setHeader(Cors.ResponseHeader.EXPOSE_HEADERS.toString(), "Header1,Header2");
    }
