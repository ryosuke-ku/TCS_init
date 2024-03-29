    public void testInitialize_設定値初期化確認()
    {
        // 準備
        DrpcTridentEmitter spiedTarget = Mockito.spy(this.target);
        Mockito.doReturn(11111L).when(spiedTarget).getCurrentTime();
        Map config = createBaseConfig();

        TopologyContext mockContext = Mockito.mock(TopologyContext.class);
        String function = "function";

        // 実施
        spiedTarget.initialize(createBaseConfig(), mockContext, function);

        // 検証
        assertEquals(mockContext, spiedTarget.context);
        assertEquals(config, spiedTarget.stormConf);
        assertEquals("function", spiedTarget.function);
        assertEquals(0, spiedTarget.idsMap.size());
        assertEquals(30000, spiedTarget.rotateTime);
        assertEquals(11111L, spiedTarget.lastRotate);
    }
