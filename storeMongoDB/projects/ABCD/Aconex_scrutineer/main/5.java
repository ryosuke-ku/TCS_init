    void verify(ElasticSearchIdAndVersionStream elasticSearchIdAndVersionStream, JdbcIdAndVersionStream jdbcIdAndVersionStream, IdAndVersionStreamVerifier idAndVersionStreamVerifier) {
        Function<Long, Object> formatter = createFormatter();
        IdAndVersionStreamVerifierListener verifierListener = createVerifierListener(formatter);

        idAndVersionStreamVerifier.verify(jdbcIdAndVersionStream, elasticSearchIdAndVersionStream, verifierListener);
    }
