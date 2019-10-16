    public void testMongoWriteConcern() throws Exception {
        Map<String, String> config = mapBuilder("playjongo.uri", "mongodb://localhost:27017/foo")
                .with("playjongo.defaultWriteConcern", "REPLICAS_SAFE").get();
        final PlayJongo cut = playJongo(config, false);

        assertThat(cut.jongo.getDatabase().getWriteConcern()).isEqualTo(WriteConcern.SAFE);
    }
