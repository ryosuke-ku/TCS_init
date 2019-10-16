    public void testInitialize_�ݒ�l�������m�F()
    {
        // ����
        DrpcTridentEmitter spiedTarget = Mockito.spy(this.target);
        Mockito.doReturn(11111L).when(spiedTarget).getCurrentTime();
        Map config = createBaseConfig();

        TopologyContext mockContext = Mockito.mock(TopologyContext.class);
        String function = "function";

        // ���{
        spiedTarget.initialize(createBaseConfig(), mockContext, function);

        // ����
        assertEquals(mockContext, spiedTarget.context);
        assertEquals(config, spiedTarget.stormConf);
        assertEquals("function", spiedTarget.function);
        assertEquals(0, spiedTarget.idsMap.size());
        assertEquals(30000, spiedTarget.rotateTime);
        assertEquals(11111L, spiedTarget.lastRotate);
    }
