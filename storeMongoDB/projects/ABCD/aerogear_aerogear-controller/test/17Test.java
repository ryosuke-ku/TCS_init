    public void maxAge() {
        when(corsConfig.getMaxAge()).thenReturn(600L);
        when(corsConfig.hasMaxAge()).thenReturn(true);
        cors.setMaxAge(response);
        verify(response).setHeader(Cors.ResponseHeader.MAX_AGE.toString(), "600");
    }
