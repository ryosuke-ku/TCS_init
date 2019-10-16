    public void testAddRemoveMixin() throws RepositoryException {
        // add mix:title to a nt:folder node and set jcr:title property
        Node n = testRootNode.addNode(nodeName1, "nt:folder");
        n.addMixin("mix:title");
        n.setProperty("jcr:title", "blah blah");
        testRootNode.getSession().save();

        // remove mix:title, jcr:title should be gone as there's no matching
        // definition in nt:folder
        n.removeMixin("mix:title");
        testRootNode.getSession().save();
        assertFalse(n.hasProperty("jcr:title"));

        // add mix:title to a nt:unstructured node and set jcr:title property
        Node n1 = testRootNode.addNode(nodeName2, ntUnstructured);
        n1.addMixin("mix:title");
        n1.setProperty("jcr:title", "blah blah");
        assertEquals(
                n1.getProperty("jcr:title").getDefinition().getDeclaringNodeType().getName(),
                "mix:title");

        // remove mix:title, jcr:title should stay since it adopts the residual
        // property definition declared in nt:unstructured
        testRootNode.getSession().save();

        n1.removeMixin("mix:title");
        testRootNode.getSession().save();
        assertTrue(n1.hasProperty("jcr:title"));

        assertEquals(
                n1.getProperty("jcr:title").getDefinition().getDeclaringNodeType().getName(),
                ntUnstructured);

        // add mix:referenceable to a nt:unstructured node, jcr:uuid is
        // automatically added
        Node n2 = testRootNode.addNode(nodeName3, ntUnstructured);
        n2.addMixin(mixReferenceable);
        testRootNode.getSession().save();

        // remove mix:referenceable, jcr:uuid should always get removed
        // since it is a protcted property
        n2.removeMixin(mixReferenceable);
        testRootNode.getSession().save();
        assertFalse(n2.hasProperty("jcr:uuid"));
    }
