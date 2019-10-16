    public void allowCredentials() {
        when(corsConfig.allowCookies()).thenReturn(true);
        cors.setAllowCredentials(response);
        verify(response).setHeader(Cors.ResponseHeader.ALLOW_CREDENTIALS.toString(), Boolean.TRUE.toString());
    }
