    public void echoOrigin() {
        cors.setEchoOrigin(response);
        verify(response).setHeader(Cors.ResponseHeader.ALLOW_ORIGIN.toString(), "http://someserver.com");
    }
