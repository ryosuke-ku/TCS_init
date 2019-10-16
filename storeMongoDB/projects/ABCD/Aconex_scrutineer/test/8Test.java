    public void testVerify() {
        Scrutineer scrutineer = spy(new Scrutineer(options));
        doReturn(elasticSearchIdAndVersionStream).when(scrutineer).createElasticSearchIdAndVersionStream(eq(options));
        doReturn(jdbcIdAndVersionStream).when(scrutineer).createJdbcIdAndVersionStream(options);
        doNothing().when(scrutineer).verify(eq(elasticSearchIdAndVersionStream), eq(jdbcIdAndVersionStream), any(IdAndVersionStreamVerifier.class));
        scrutineer.verify();

        verify(scrutineer).verify(eq(elasticSearchIdAndVersionStream), eq(jdbcIdAndVersionStream), any(IdAndVersionStreamVerifier.class));

    }
