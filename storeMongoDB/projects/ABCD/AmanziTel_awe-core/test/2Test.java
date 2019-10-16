    public void testCheckUnderlyingDatabaseExceptionOnInitializeFromString() throws Exception {
        Node parentNode = getNodeMock();
        doThrow(new DatabaseException(new IllegalArgumentException())).when(nodeService).createNode(parentNode, TEST_NODE_TYPE,
                NodeService.NodeServiceRelationshipType.CHILD, TEST_NODE_NAME);

        model.initialize(parentNode, TEST_NODE_NAME, TEST_NODE_TYPE);
    }
