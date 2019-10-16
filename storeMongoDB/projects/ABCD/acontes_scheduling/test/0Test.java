    public void action() throws Exception {
        // building graph
        Node n1 = new DummyNode("1");
        Node n2 = new DummyNode("2");
        Node n3 = new DummyNode("3");
        distances.put(n1, new HashMap<Node, Long>());
        distances.put(n2, new HashMap<Node, Long>());
        distances.put(n3, new HashMap<Node, Long>());
        distances.get(n1).put(n2, new Long(2));
        distances.get(n1).put(n3, new Long(4));
        distances.get(n2).put(n3, new Long(-1));

        HAC hac = new HAC(new LocalTopology(), null, BestProximityDescriptor.AVG, Long.MAX_VALUE);
        HAC hacPivot = new HAC(new LocalTopology(), Collections.singletonList(n1),
            BestProximityDescriptor.AVG, Long.MAX_VALUE);
        RMTHelper.log("Test 1: [no pivot], graph [1 -(2)- 2 , 1 -(4)- 3]");
        List<Node> result = hac.select(20, new LinkedList<Node>(distances.keySet()));
        Assert.assertTrue("Selection size is not 2", result.size() == 2);
        if (!(result.contains(new DummyNode("1")) && result.contains(new DummyNode("2")))) {
            Assert.assertTrue("Selection is incorrect", false);
        }

        RMTHelper.log("Test 2: [pivot - node 1], graph [1 -(2)- 2 , 1 -(4)- 3]");
        result = hacPivot.select(3, new LinkedList<Node>(distances.keySet()));
        Assert.assertTrue("Selection size is not 1", result.size() == 1);
        if (!result.contains(new DummyNode("2"))) {
            Assert.assertTrue("Selection is incorrect", false);
        }

        distances.get(n2).put(n3, new Long(10));
        RMTHelper.log("Test 3: [no pivot], graph [1 -(2)- 2 , 1 -(4)- 3, 2 -(10)- 3]");
        result = hac.select(3, new LinkedList<Node>(distances.keySet()));
        Assert.assertTrue("Selection size is not 3", result.size() == 3);

        RMTHelper.log("Test 4: [pivot - node 1], graph [1 -(2)- 2 , 1 -(4)- 3, 2 -(10)- 3]");
        result = hacPivot.select(3, new LinkedList<Node>(distances.keySet()));
        Assert.assertTrue("Selection size is not 2", result.size() == 2);
        if (!(result.contains(new DummyNode("2")) && result.contains(new DummyNode("3")))) {
            Assert.assertTrue("Selection is incorrect", false);
        }

        Node n4 = new DummyNode("4");
        distances.put(n4, new HashMap<Node, Long>());
        distances.get(n2).put(n4, new Long(1));
        distances.get(n3).put(n4, new Long(3));
        distances.get(n1).put(n4, new Long(-1));
        RMTHelper.log("Test 5: [no pivot], graph [1 -(2)- 2 , 1 -(4)- 3, 2 -(10)- 3, 2 -(1)- 4, 3 -(3)- 4]");
        result = hac.select(4, new LinkedList<Node>(distances.keySet()));
        // HAC cannot cluster 3 nodes together so the expected result is 2
        Assert.assertTrue("Selection size is not 2", result.size() == 2);

        RMTHelper
                .log("Test 6: [pivot - node 1], graph [1 -(2)- 2 , 1 -(4)- 3, 2 -(10)- 3, 2 -(1)- 4, 3 -(3)- 4]");
        result = hacPivot.select(3, new LinkedList<Node>(distances.keySet()));
        Assert.assertTrue("Selection size is not 2", result.size() == 2);
        if (!(result.contains(new DummyNode("2")) && result.contains(new DummyNode("3")))) {
            Assert.assertTrue("Selection is incorrect", false);
        }

        distances.get(n1).put(n4, new Long(3));
        RMTHelper
                .log("Test 7: [no pivot], graph [1 -(2)- 2 , 1 -(4)- 3, 2 -(10)- 3, 2 -(1)- 4, 3 -(3)- 4, 1 -(3)- 4]]");
        result = hac.select(4, new LinkedList<Node>(distances.keySet()));
        Assert.assertTrue("Selection size is not 4", result.size() == 4);

        RMTHelper
                .log("Test 8 - optimal: [no pivot], graph [1 -(2)- 2 , 1 -(4)- 3, 2 -(10)- 3, 2 -(1)- 4, 3 -(3)- 4, 1 -(3)- 4]]");
        result = hac.select(3, new LinkedList<Node>(distances.keySet()));
        Assert.assertTrue("Selection size is not 3", result.size() == 3);
        if (!(result.contains(new DummyNode("1")) && result.contains(new DummyNode("2")) && result
                .contains(new DummyNode("4")))) {
            Assert.assertTrue("Selection is incorrect", false);
        }

        RMTHelper
                .log("Test 8: [pivot - node 1], graph [1 -(2)- 2 , 1 -(4)- 3, 2 -(10)- 3, 2 -(1)- 4, 3 -(3)- 4, 1 -(3)- 4]]");
        result = hacPivot.select(2, new LinkedList<Node>(distances.keySet()));
        Assert.assertTrue("Selection size is not 2", result.size() == 2);
        if (!(result.contains(new DummyNode("2")) && result.contains(new DummyNode("4")))) {
            Assert.assertTrue("Selection is incorrect", false);
        }

        distances.get(n1).put(n4, new Long(30));
        RMTHelper
                .log("Test 9 - optimal: [no pivot], graph [1 -(2)- 2 , 1 -(4)- 3, 2 -(10)- 3, 2 -(1)- 4, 3 -(3)- 4, 1 -(30)- 4]");
        result = hac.select(3, new LinkedList<Node>(distances.keySet()));
        Assert.assertTrue("Selection size is not 3", result.size() == 3);
        if (!(result.contains(new DummyNode("2")) && result.contains(new DummyNode("3")) && result
                .contains(new DummyNode("4")))) {
            Assert.assertTrue("Selection is incorrect", false);
        }

        Node n5 = new DummyNode("5");
        distances.put(n5, new HashMap<Node, Long>());

        hacPivot = new HAC(new LocalTopology(), Collections.singletonList(n5), BestProximityDescriptor.AVG,
            Long.MAX_VALUE);
        distances.get(n5).put(n1, new Long(-1));
        distances.get(n5).put(n2, new Long(-1));
        distances.get(n5).put(n3, new Long(10));
        distances.get(n5).put(n4, new Long(10));
        RMTHelper
                .log("Test 10: [pivot - node 5], graph [1 -(2)- 2 , 1 -(4)- 3, 2 -(10)- 3, 2 -(1)- 4, 3 -(3)- 4, 1 -(30)- 4, 5 -(10)- 3, 5 -(10)- 4]");
        result = hacPivot.select(4, new LinkedList<Node>(distances.keySet()));
        Assert.assertTrue("Selection size is not 2", result.size() == 2);
        if (!(result.contains(new DummyNode("3")) && result.contains(new DummyNode("4")))) {
            Assert.assertTrue("Selection is incorrect", false);
        }
    }
