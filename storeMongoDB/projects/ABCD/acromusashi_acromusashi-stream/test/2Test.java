    public void testEmitBatch_リクエストなし() throws TException
    {
        // 準備
        DrpcTridentEmitter spiedTarget = Mockito.spy(this.target);
        DrpcFetchHelper mockHelper = Mockito.mock(DrpcFetchHelper.class);
        Mockito.doReturn(11111L).when(spiedTarget).getCurrentTime();
        Mockito.doReturn(mockHelper).when(spiedTarget).createFetchHelper();

        TopologyContext mockContext = Mockito.mock(TopologyContext.class);
        String function = "function";
        spiedTarget.initialize(createBaseConfig(), mockContext, function);

        TransactionAttempt attempt = new TransactionAttempt(11111L, 111);
        TridentCollector mockCollector = Mockito.mock(TridentCollector.class);
        Object meta = new Object();

        // 実施
        spiedTarget.emitBatch(attempt, meta, mockCollector);

        // 検証
        assertTrue(spiedTarget.prepared);
        assertNotNull(spiedTarget.fetchHelper);
        Mockito.verify(mockHelper).fetch();
        Mockito.verify(spiedTarget, times(0)).emitTuples("function", mockCollector);
    }
