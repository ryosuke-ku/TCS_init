    public void testReferentialIntegrityCorruptionGetPath() throws Exception {
        Session session = testRootNode.getSession();
        Node root = testRootNode.addNode("testReferentialIntegrityCorruption");

        // Create test nodes P1 and P2
        Node nodeP1 = root.addNode("P1");
        nodeP1.addMixin("mix:referenceable");
        Node nodeP2 = root.addNode("P2");
        nodeP2.addMixin("mix:referenceable");
        session.save();

        // Create reference from P2 to P1 and save
        nodeP2.setProperty("referencetoP1", nodeP1);
        session.save();

        // Add node P3
        Node nodeP3 = root.addNode("P3");
        nodeP3.addMixin("mix:referenceable");

        String nodeP3path = nodeP3.getPath();

        // And try to remove P1 while P2 still references P1
        nodeP1.remove();
        try {
            session.save();
        } catch (ReferentialIntegrityException expected) {
            // Got ReferentialIntegrityException as expected
        }

        // Remove P2 and save again, this will succeed. As P1, P2
        // should be removed and P3 should exist
        try {
            nodeP2.remove();
            session.save();
        } catch (Exception e) {
            String msg = "JCR-3018: Saving delete after"
                    + " ReferentialIntegrityException failed";
            log.error(msg, e);
            fail(msg);
        }

        try {
            assertEquals(nodeP3path, nodeP3.getPath());
            nodeP3 = session.getNodeByIdentifier(nodeP3.getIdentifier());
        } catch (Exception e) {
            String msg = "JCR-3018: getting path of P3 failed. Corrupt session?";
            log.error(msg, e);
            fail(msg);
        }

        root.remove();
        session.save();
    }
