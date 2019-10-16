    public void testInitializationFromName() throws Exception {
        Node parentNode = getNodeMock();

        model.initialize(parentNode, MODEL_NAME);

        verify(nodeService).createNode(parentNode, TestNodeTypes.TEST1, NodeService.NodeServiceRelationshipType.CHILD, MODEL_NAME);
    }
