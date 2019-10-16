    public void mapPathParamsSingleParam() {
        final Map<String, String> params = RequestUtils.mapPathParams("/cars/red", "/cars/{color}");
        assertThat(params.get("color")).isEqualTo("red");
        assertThat(params.size()).isEqualTo(1);
    }
