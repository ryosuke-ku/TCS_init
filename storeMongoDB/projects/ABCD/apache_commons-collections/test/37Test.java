    public void selectWithOutputCollections() {
        List<Integer> input = new ArrayList<Integer>();
        input.add(1);
        input.add(2);
        input.add(3);
        input.add(4);
        
        List<Integer> output = new ArrayList<Integer>();
        List<Integer> rejected = new ArrayList<Integer>();

        CollectionUtils.select(input, EQUALS_TWO, output, rejected);

        // output contains 2
        assertEquals(1, output.size());
        assertEquals(2, CollectionUtils.extractSingleton(output).intValue());
        
        // rejected contains 1, 3, and 4
        Integer[] expected = {1, 3, 4};
        Assert.assertArrayEquals(expected, rejected.toArray());
        
        output.clear();
        rejected.clear();
        CollectionUtils.select((List<Integer>) null, EQUALS_TWO, output, rejected);
        assertTrue(output.isEmpty());
        assertTrue(rejected.isEmpty());
    }
