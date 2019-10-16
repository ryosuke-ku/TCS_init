    public void testSuccess_���N�G�X�g����() throws TException
    {
        // ����
        DrpcTridentEmitter spiedTarget = Mockito.spy(this.target);
        DrpcFetchHelper mockHelper = Mockito.mock(DrpcFetchHelper.class);
        Mockito.doReturn(11111L).when(spiedTarget).getCurrentTime();
        Mockito.doReturn(mockHelper).when(spiedTarget).createFetchHelper();
        DrpcRequestInfo requestInfo = new DrpcRequestInfo();
        requestInfo.setRequestId("11111");
        requestInfo.setFuncArgs("funcArg");
        Mockito.doReturn(requestInfo).when(mockHelper).fetch();

        TopologyContext mockContext = Mockito.mock(TopologyContext.class);
        String function = "function";
        spiedTarget.initialize(createBaseConfig(), mockContext, function);

        TransactionAttempt attempt = new TransactionAttempt(11111L, 111);
        TridentCollector mockCollector = Mockito.mock(TridentCollector.class);
        Object meta = new Object();

        // ���{
        spiedTarget.emitBatch(attempt, meta, mockCollector);
        spiedTarget.success(attempt);

        // ����
        Mockito.verify(mockHelper).ack("11111", "Succeeded");
        assertEquals(0, spiedTarget.idsMap.size());
    }
