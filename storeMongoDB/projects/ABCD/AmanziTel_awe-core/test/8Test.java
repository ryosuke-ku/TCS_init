    public void testCheckModelAsDataElement() throws Exception {
        Node rootNode = getNodeMock();

        model.initialize(rootNode);

        assertNotNull("data element cannot be null", model.asDataElement());

        IDataElement result = model.asDataElement();

        assertTrue("unexpected class", result instanceof DataElement);

        assertEquals("unexpected node", rootNode, ((DataElement)result).getNode());
    }
