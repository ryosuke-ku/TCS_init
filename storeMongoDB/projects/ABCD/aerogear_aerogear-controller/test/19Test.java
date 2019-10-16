    public void areRequestHeadersValid() {
        when(request.getHeader(Cors.RequestHeader.HEADERS.toString())).thenReturn("origin, X-Header2");
        assertThat(cors.areRequestHeadersValid(Arrays.asList("HEADER1", "x-header2", "origin"))).isTrue();
    }
