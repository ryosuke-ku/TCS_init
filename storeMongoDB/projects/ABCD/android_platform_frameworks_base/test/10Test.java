    public void testValueBackRefs() {
        ContentValues values = new ContentValues();
        values.put("a", "in1");
        values.put("a2", "in2");
        values.put("b", "in3");
        values.put("c", "in4");

        ContentProviderResult[] previousResults = new ContentProviderResult[4];
        previousResults[0] = new ContentProviderResult(100);
        previousResults[1] = new ContentProviderResult(101);
        previousResults[2] = new ContentProviderResult(102);
        previousResults[3] = new ContentProviderResult(103);

        ContentValues expectedValues = new ContentValues(values);
        expectedValues.put("a1", (long) 103);
        expectedValues.put("a2", (long) 101);
        expectedValues.put("a3", (long) 102);

        ContentProviderOperation op1 = ContentProviderOperation.newInsert(sTestUri1)
                .withValues(values)
                .withValueBackReference("a1", 3)
                .withValueBackReference("a2", 1)
                .withValueBackReference("a3", 2)
                .build();
        ContentValues v2 = op1.resolveValueBackReferences(previousResults, previousResults.length);
        assertEquals(expectedValues, v2);
    }
