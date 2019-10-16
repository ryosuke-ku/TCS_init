    public void testCheckModelFieldsOnInitialize() throws Exception {
        Node rootNode = getNodeMock();
        Node parentNode = getNodeMock();

        when(nodeService.getNodeName(eq(rootNode))).thenReturn(TEST_NODE_NAME);
        when(nodeService.getNodeType(eq(rootNode))).thenReturn(TEST_NODE_TYPE);
        doReturn(parentNode).when(model).getParent(rootNode);

        model.initialize(rootNode);

        assertEquals("Unexpected initialized name", TEST_NODE_NAME, model.getName());
        assertEquals("Unexpected initialized type", TEST_NODE_TYPE, model.getType());
        assertEquals("Unexpected initialized parent node", parentNode, model.getParentNode());
        assertEquals("Unexpected initialized root node", rootNode, model.getRootNode());
    }
