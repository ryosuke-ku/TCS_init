    public void testInitialize_İ’è’l‰Šú‰»Šm”F()
    {
        // €”õ
        DrpcTridentEmitter spiedTarget = Mockito.spy(this.target);
        Mockito.doReturn(11111L).when(spiedTarget).getCurrentTime();
        Map config = createBaseConfig();

        TopologyContext mockContext = Mockito.mock(TopologyContext.class);
        String function = "function";

        // À{
        spiedTarget.initialize(createBaseConfig(), mockContext, function);

        // ŒŸØ
        assertEquals(mockContext, spiedTarget.context);
        assertEquals(config, spiedTarget.stormConf);
        assertEquals("function", spiedTarget.function);
        assertEquals(0, spiedTarget.idsMap.size());
        assertEquals(30000, spiedTarget.rotateTime);
        assertEquals(11111L, spiedTarget.lastRotate);
    }
