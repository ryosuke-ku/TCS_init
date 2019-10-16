    public void testIsCheckedOut() throws RepositoryException, NotExecutableException {
        Node n = testRootNode.addNode(nodeName1);
        NodeImpl testNode = (NodeImpl) n.addNode(nodeName2);
        testRootNode.save();

        Principal principal = getReadOnlyPrincipal(getHelper());
        changeReadPermission(principal, n, false);
        changeReadPermission(principal, testNode, true);

        Session readOnly = getHelper().getReadOnlySession();
        try {
            NodeImpl tn = (NodeImpl) readOnly.getItem(testNode.getPath());
            assertTrue(tn.isCheckedOut());

            n.addMixin(mixVersionable);
            testRootNode.save();
            n.checkin();

            assertFalse(tn.isCheckedOut());
        } finally {
            readOnly.logout();
            // reset the denied read-access
            n.checkout();
            changeReadPermission(principal, n, true);
        }
    }
