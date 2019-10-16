    public void testCollateIgnoreDuplicates() {
        List<Integer> result1 = CollectionUtils.collate(collectionD, collectionE, false);
        List<Integer> result2 = CollectionUtils.collate(collectionE, collectionD, false);
        assertEquals("Merge two lists 1 - ignore duplicates", result1, result2);

        Set<Integer> combinedSet = new HashSet<Integer>();
        combinedSet.addAll(collectionD);
        combinedSet.addAll(collectionE);
        List<Integer> combinedList = new ArrayList<Integer>(combinedSet);
        Collections.sort(combinedList);

        assertEquals("Merge two lists 2 - ignore duplicates", combinedList, result2);
    }
