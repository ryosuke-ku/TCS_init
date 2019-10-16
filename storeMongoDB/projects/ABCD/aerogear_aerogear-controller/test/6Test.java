    public void extractPlaceHoldersWithSubpath() {
        final Set<String> params = RequestUtils.extractPlaceHolders("/cars/{color}/subpath/{brand}");
        assertThat(params).contains("color", "brand");
        assertThat(params.size()).isEqualTo(2);
    }
