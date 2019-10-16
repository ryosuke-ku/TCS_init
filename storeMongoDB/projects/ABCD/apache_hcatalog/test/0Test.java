    protected void assertOrderedNodeNames(String[] names, String[] expected) {
        int size = names.length;
        assertEquals("The two arrays should be the same size!", names.length, expected.length);
        SortedSet<ZNodeName> nodeNames = new TreeSet<ZNodeName>();
        for (String name : names) {
            nodeNames.add(new ZNodeName(name));
        }

        int index = 0;
        for (ZNodeName nodeName : nodeNames) {
            String name = nodeName.getName();
            assertEquals("Node " + index, expected[index++], name);
        }
    }
