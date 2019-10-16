    public void setOriginEcho() {
        CorsTestUtil.setOriginRequestHeader(request, "http://myserver.com");
        when(corsConfig.anyOrigin()).thenReturn(false);
        cors.setOrigin(response);
        verify(response).setHeader(Cors.ResponseHeader.ALLOW_ORIGIN.toString(), "http://myserver.com");
    }
