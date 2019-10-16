    public void acceptsMediaTypeAny() {
        assertThat(RequestUtils.acceptsMediaType(acceptHeaders("*/*, application/json"), produces(MediaType.HTML))).isTrue();
    }
