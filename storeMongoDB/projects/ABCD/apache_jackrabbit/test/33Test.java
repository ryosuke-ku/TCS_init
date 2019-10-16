    public void testBracketsInNodeName() throws Exception {
        final Node root = testRootNode.addNode("testBracketsInNodeName");

        final String[] childNames = { "{A}", "B}", "{C", "(D)", "E)", "(F", };

        for (String name : childNames) {
            root.addNode(name);
            root.getSession().save();
            assertTrue("Expecting child " + name + " to have been created", root.hasNode(name));
        }
    }
