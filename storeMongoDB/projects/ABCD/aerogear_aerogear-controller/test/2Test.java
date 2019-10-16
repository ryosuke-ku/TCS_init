    public void extractAcceptsHeader() {
        when(request.getHeader("Accept")).thenReturn("application/json, application/xml");
        assertThat(RequestUtils.extractAcceptHeader(request)).contains(MediaType.JSON.getType(), "application/xml");
        assertThat(RequestUtils.extractAcceptHeader(request).size()).isEqualTo(2);
    }
