    public void testEmitBatch_リクエスト受信後タイムアウト_応答失敗() throws TException
    {
        // 準備
        ch.qos.logback.classic.Logger root = (ch.qos.logback.classic.Logger) org.slf4j.LoggerFactory.getLogger(ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME);
        Appender<ILoggingEvent> mockAppender = Mockito.mock(Appender.class);
        Mockito.when(mockAppender.getName()).thenReturn("MOCK");
        root.addAppender(mockAppender);

        DrpcTridentEmitter spiedTarget = Mockito.spy(this.target);
        DrpcFetchHelper mockHelper = Mockito.mock(DrpcFetchHelper.class);
        Mockito.when(spiedTarget.getCurrentTime()).thenReturn(11111L).thenReturn(11111L).thenReturn(
                41112L);
        Mockito.doReturn(mockHelper).when(spiedTarget).createFetchHelper();
        DrpcRequestInfo requestInfo = new DrpcRequestInfo();
        requestInfo.setRequestId("11111");
        requestInfo.setFuncArgs("funcArg");
        Mockito.when(mockHelper.fetch()).thenReturn(requestInfo).thenReturn(null);
        Mockito.doThrow(new TException()).when(mockHelper).fail("11111");

        TopologyContext mockContext = Mockito.mock(TopologyContext.class);
        String function = "function";
        spiedTarget.initialize(createBaseConfig(), mockContext, function);

        TransactionAttempt attempt1 = new TransactionAttempt(11111L, 111);
        TridentCollector mockCollector = Mockito.mock(TridentCollector.class);
        Object meta = new Object();

        spiedTarget.emitBatch(attempt1, meta, mockCollector);

        // 時間経過を模倣
        spiedTarget.idsMap.rotate();
        spiedTarget.idsMap.rotate();

        // 実施
        TransactionAttempt attempt2 = new TransactionAttempt(22222L, 222);
        spiedTarget.emitBatch(attempt2, meta, mockCollector);

        // 検証
        assertTrue(spiedTarget.prepared);
        assertNotNull(spiedTarget.fetchHelper);
        assertEquals(0, spiedTarget.idsMap.size());
        Mockito.verify(mockHelper).fail("11111");

        ArgumentCaptor<ILoggingEvent> eventCaptor = ArgumentCaptor.forClass(ILoggingEvent.class);
        Mockito.verify(mockAppender).doAppend(eventCaptor.capture());

        ILoggingEvent actualEvent = eventCaptor.getValue();
        String actual = actualEvent.toString();

        assertThat(
                actual,
                equalTo("[WARN] Fail notify failed. TransactionAttempt=11111:111, DrpcRequestInfo=DrpcRequestInfo[requestId=11111,funcArgs=funcArg]"));
    }
