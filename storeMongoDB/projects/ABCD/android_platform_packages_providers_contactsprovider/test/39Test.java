    public void testBuildSingleRowResult() {
        checkBuildSingleRowResult(
                new String[] {"b"},
                new String[] {"a", "b"},
                new Integer[] {1, 2},
                new Integer[] {2}
                );

        checkBuildSingleRowResult(
                new String[] {"b", "a", "b"},
                new String[] {"a", "b"},
                new Integer[] {1, 2},
                new Integer[] {2, 1, 2}
                );

        checkBuildSingleRowResult(
                null, // all columns
                new String[] {"a", "b"},
                new Integer[] {1, 2},
                new Integer[] {1, 2}
                );

        try {
            // Access non-existent column
            ContactsProvider2.buildSingleRowResult(new String[] {"a"}, new String[] {"b"},
                    new Object[] {1});
            fail();
        } catch (IllegalArgumentException expected) {
        }
    }
