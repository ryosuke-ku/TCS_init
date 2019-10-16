    public void testAddNodeUuidCollision() throws RepositoryException, NotExecutableException {
        String uuid = "f81d4fae-7dec-11d0-a765-00a0c91e6bf6";
        Node n = testRootNode.addNode(nodeName1);
        Node testNode1 = ((NodeImpl) n).addNodeWithUuid(nodeName2, uuid);
        testNode1.addMixin(NodeType.MIX_REFERENCEABLE);
        testRootNode.getSession().save();

        try {
            ((NodeImpl) n).addNodeWithUuid(nodeName2, uuid);
            fail("UUID collision not detected by addNodeWithUuid");
        } catch (ItemExistsException e) {
        }
    }
