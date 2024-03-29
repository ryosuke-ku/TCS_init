    public void test_mergeA() {
        ListIntSet setA = new ListIntSet();
        int[] valuesA = {0, 1, 31};

        for (int i = 0; i < valuesA.length; i++) {
            setA.add(valuesA[i]);
        }

        ListIntSet setB = new ListIntSet();
        int[] valuesB = {0, 5, 6, 32, 127, 128};

        for (int i = 0; i < valuesB.length; i++) {
            setB.add(valuesB[i]);
        }

        setA.merge(setB);

        for (int i = 0; i < valuesA.length; i++) {
            assertTrue(setA.has(valuesA[i]));
        }

        for (int i = 0; i < valuesB.length; i++) {
            assertTrue(setA.has(valuesB[i]));
        }

    }
