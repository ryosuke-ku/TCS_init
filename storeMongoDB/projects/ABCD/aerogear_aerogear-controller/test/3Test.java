    public void extractPathSegmentWithPlaceHoldersAndSubpathAndQueryParam() {
        final Map<Integer, String> params = RequestUtils.extractPathSegments("/cars/red/subpath/ferrari?name=Fletch");
        assertThat(params.get(0)).isEqualTo("cars");
        assertThat(params.get(1)).isEqualTo("red");
        assertThat(params.get(2)).isEqualTo("subpath");
        assertThat(params.get(3)).isEqualTo("ferrari");
        assertThat(params.size()).isEqualTo(4);
    }
