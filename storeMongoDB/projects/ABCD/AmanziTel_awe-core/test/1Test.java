    public void setUp() {
        nodeService = mock(INodeService.class);

        model = spy(new TestNamedModel(nodeService, new GeneralNodeProperties()));
        doReturn(TestNodeTypes.TEST1).when(model).getModelType();
    }
