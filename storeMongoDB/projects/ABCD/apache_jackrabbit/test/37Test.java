    public void testSetPropertyExplicitType() throws RepositoryException {
        Node n = testRootNode.addNode(nodeName1, ntUnstructured);
        n.addMixin("mix:title");
        Property p = n.setProperty("jcr:title", "foo");
        assertEquals(PropertyType.nameFromValue(PropertyType.STRING),
                PropertyType.nameFromValue(p.getType()));
        assertEquals(PropertyType.nameFromValue(PropertyType.STRING),
                PropertyType.nameFromValue(p.getDefinition().getRequiredType()));
        p.remove();
        // must use residual definition from nt:unstructured
        p = n.setProperty("jcr:title", 123);
        assertEquals(PropertyType.nameFromValue(PropertyType.LONG),
                PropertyType.nameFromValue(p.getType()));
        assertEquals(PropertyType.nameFromValue(PropertyType.UNDEFINED),
                PropertyType.nameFromValue(p.getDefinition().getRequiredType()));
    }
