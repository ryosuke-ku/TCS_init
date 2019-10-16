    public void extractMethod() {
        when(request.getMethod()).thenReturn("GET");
        assertThat(RequestUtils.extractMethod(request)).isEqualTo(RequestMethod.GET);
        when(request.getMethod()).thenReturn("PUT");
        assertThat(RequestUtils.extractMethod(request)).isEqualTo(RequestMethod.PUT);
        when(request.getMethod()).thenReturn("POST");
        assertThat(RequestUtils.extractMethod(request)).isEqualTo(RequestMethod.POST);
        when(request.getMethod()).thenReturn("DELETE");
        assertThat(RequestUtils.extractMethod(request)).isEqualTo(RequestMethod.DELETE);
        when(request.getMethod()).thenReturn("OPTIONS");
        assertThat(RequestUtils.extractMethod(request)).isEqualTo(RequestMethod.OPTIONS);
        when(request.getMethod()).thenReturn("HEAD");
        assertThat(RequestUtils.extractMethod(request)).isEqualTo(RequestMethod.HEAD);
        when(request.getMethod()).thenReturn("PATCH");
        assertThat(RequestUtils.extractMethod(request)).isEqualTo(RequestMethod.PATCH);
    }
