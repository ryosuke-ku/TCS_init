    public void testSetPropertyConvertMultiValued() throws RepositoryException {
        Node n = testRootNode.addNode(nodeName1, "test:canSetProperty");
        // must convert to long there is no other definition for this property
        Property p = n.setProperty("LongMultiple", new String[]{"123", "456"});
        assertEquals(PropertyType.nameFromValue(PropertyType.LONG),
                PropertyType.nameFromValue(p.getType()));
    }
