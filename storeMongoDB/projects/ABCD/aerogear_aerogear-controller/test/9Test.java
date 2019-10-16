    public void hasRequestHeaders() {
        when(request.getHeader(Cors.RequestHeader.HEADERS.toString())).thenReturn(null);
        assertThat(cors.hasRequestHeaders()).isFalse();
        when(request.getHeader(Cors.RequestHeader.HEADERS.toString())).thenReturn("X-Custom-Header");
        assertThat(cors.hasRequestHeaders()).isTrue();
    }
