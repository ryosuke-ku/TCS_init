    public void anyOrigin() {
        cors.setAnyOrigin(response);
        verify(response).setHeader(Cors.ResponseHeader.ALLOW_ORIGIN.toString(), "*");
    }
