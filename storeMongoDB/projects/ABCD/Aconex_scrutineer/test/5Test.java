    public void testShouldUseStandardPrintStreamListenerIfOptionProvided() {
        Scrutineer scrutineer = spy(new Scrutineer(options));

        doReturn(coincidentListener).when(scrutineer).createCoincidentPrintStreamListener(any(Function.class));
        doReturn(standardListener).when(scrutineer).createStandardPrintStreamListener(any(Function.class));

        scrutineer.verify(elasticSearchIdAndVersionStream, jdbcIdAndVersionStream, idAndVersionStreamVerifier);

        verify(scrutineer).verify(elasticSearchIdAndVersionStream, jdbcIdAndVersionStream, idAndVersionStreamVerifier);
        verify(idAndVersionStreamVerifier).verify(any(IdAndVersionStream.class), any(IdAndVersionStream.class), eq(standardListener));
    }
