    public void segmentsMatchTrailingSlash() {
        final boolean matches = RequestUtils.segmentsMatch("/cars/{color}/{brand}", "/cars/red/BMW/");
        assertThat(matches).isTrue();
    }
