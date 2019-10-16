	public void testOrdering() {
        Integer[] keys = new Integer[101];
        TreeMap<Integer, Integer> testMap = new TreeMap<Integer, Integer>(new Comparator<Integer>() {

            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        
        java.util.TreeMap<Integer, Integer> refMap = new java.util.TreeMap<Integer, Integer>(new Comparator<Integer>() {

            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        
        for (int i = 0; i < keys.length; i++) {
            keys[i] = i * 2;
            testMap.put(keys[i], keys[i]);
            refMap.put(keys[i], keys[i]);
        }
        
        assertEquals(testMap.get(4), refMap.get(4));
        assertEquals(testMap.get(3), refMap.get(3));
        assertEquals(testMap.size(), refMap.size());
        
        //Test lookup:
        assertEquals(null, testMap.lowerEntry(-2));
        assertEquals(null, testMap.lowerEntry(-1));
        assertEquals(null, testMap.lowerEntry(0));
        assertEquals(new Integer(0), testMap.lowerEntry(1).getValue());
        assertEquals(new Integer(0), testMap.lowerEntry(2).getValue());
        assertEquals(new Integer(48), testMap.floorEntry(49).getValue());
        assertEquals(new Integer(50), testMap.floorEntry(50).getValue());
        assertEquals(new Integer(50), testMap.floorEntry(51).getValue());
        assertEquals(new Integer(196), testMap.lowerEntry(198).getValue());
        assertEquals(new Integer(198), testMap.lowerEntry(199).getValue());
        assertEquals(new Integer(198), testMap.lowerEntry(200).getValue());
        assertEquals(new Integer(200), testMap.lowerEntry(201).getValue());
        assertEquals(new Integer(200), testMap.lowerEntry(202).getValue());
        
        assertEquals(null, testMap.floorEntry(-2));
        assertEquals(null, testMap.floorEntry(-1));
        assertEquals(new Integer(0), testMap.floorEntry(0).getValue());
        assertEquals(new Integer(0), testMap.floorEntry(1).getValue());
        assertEquals(new Integer(2), testMap.floorEntry(2).getValue());
        assertEquals(new Integer(48), testMap.floorEntry(49).getValue());
        assertEquals(new Integer(50), testMap.floorEntry(50).getValue());
        assertEquals(new Integer(50), testMap.floorEntry(51).getValue());
        assertEquals(new Integer(198), testMap.floorEntry(198).getValue());
        assertEquals(new Integer(198), testMap.floorEntry(199).getValue());
        assertEquals(new Integer(200), testMap.floorEntry(200).getValue());
        assertEquals(new Integer(200), testMap.floorEntry(201).getValue());
        assertEquals(new Integer(200), testMap.floorEntry(202).getValue());
        
        assertEquals(new Integer(0), testMap.upperEntry(-2).getValue());
        assertEquals(new Integer(0), testMap.upperEntry(-1).getValue());
        assertEquals(new Integer(2), testMap.upperEntry(0).getValue());
        assertEquals(new Integer(2), testMap.upperEntry(1).getValue());
        assertEquals(new Integer(4), testMap.upperEntry(2).getValue());
        assertEquals(new Integer(50), testMap.upperEntry(49).getValue());
        assertEquals(new Integer(52), testMap.upperEntry(50).getValue());
        assertEquals(new Integer(52), testMap.upperEntry(51).getValue());
        assertEquals(new Integer(200), testMap.upperEntry(198).getValue());
        assertEquals(new Integer(200), testMap.upperEntry(199).getValue());
        assertEquals(null, testMap.upperEntry(200));
        assertEquals(null, testMap.upperEntry(201));
        assertEquals(null, testMap.upperEntry(202));
        
        assertEquals(new Integer(0), testMap.ceilingEntry(-2).getValue());
        assertEquals(new Integer(0), testMap.ceilingEntry(-1).getValue());
        assertEquals(new Integer(0), testMap.ceilingEntry(0).getValue());
        assertEquals(new Integer(2), testMap.ceilingEntry(1).getValue());
        assertEquals(new Integer(2), testMap.ceilingEntry(2).getValue());
        assertEquals(new Integer(50), testMap.ceilingEntry(49).getValue());
        assertEquals(new Integer(50), testMap.ceilingEntry(50).getValue());
        assertEquals(new Integer(52), testMap.ceilingEntry(51).getValue());
        assertEquals(new Integer(198), testMap.ceilingEntry(198).getValue());
        assertEquals(new Integer(200), testMap.ceilingEntry(199).getValue());
        assertEquals(new Integer(200), testMap.ceilingEntry(200).getValue());
        assertEquals(null, testMap.ceilingEntry(201));
        assertEquals(null, testMap.ceilingEntry(202));
        
        //Test iterators:
        assertIteratorEquals(refMap.keySet().iterator(), testMap.keySet().iterator());
        assertIteratorEquals(refMap.values().iterator(), testMap.values().iterator());
        assertIteratorEquals(refMap.entrySet().iterator(), testMap.entrySet().iterator());
        
        //Test removal:
        assertEquals(refMap.remove(refMap.firstKey()), testMap.remove(testMap.firstKey()));
        Iterator<Integer> refIt = refMap.values().iterator();
        Iterator<Integer> testIt = testMap.values().iterator();
        refIt.next();
        testIt.next();
        refIt.remove();
        testIt.remove();
        assertIteratorEquals(testIt, refIt);
    }
