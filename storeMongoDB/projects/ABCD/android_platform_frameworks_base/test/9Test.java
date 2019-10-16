    public void testSelectionBackRefs() {
        ContentProviderResult[] previousResults = new ContentProviderResult[4];
        previousResults[0] = new ContentProviderResult(100);
        previousResults[1] = new ContentProviderResult(101);
        previousResults[2] = new ContentProviderResult(102);
        previousResults[3] = new ContentProviderResult(103);

        String[] selectionArgs = new String[]{"a", null, null, "b", null};

        final ContentValues values = new ContentValues();
        values.put("unused", "unused");

        ContentProviderOperation op1 = ContentProviderOperation.newUpdate(sTestUri1)
                .withSelectionBackReference(1, 3)
                .withSelectionBackReference(2, 1)
                .withSelectionBackReference(4, 2)
                .withSelection("unused", selectionArgs)
                .withValues(values)
                .build();
        String[] s2 = op1.resolveSelectionArgsBackReferences(
                previousResults, previousResults.length);
        assertEquals("a,103,101,b,102", TextUtils.join(",", s2));
    }
