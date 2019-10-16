    public void testInsertCarriers() {
        // insert test contentValues
        ContentValues contentValues = new ContentValues();
        final String insertApn = "exampleApnName";
        final String insertName = "exampleName";
        final Integer insertCurrent = 1;
        final String insertNumeric = "123456";
        contentValues.put(Carriers.APN, insertApn);
        contentValues.put(Carriers.NAME, insertName);
        contentValues.put(Carriers.CURRENT, insertCurrent);
        contentValues.put(Carriers.NUMERIC, insertNumeric);

        Log.d(TAG, "testInsertCarriers Inserting contentValues: " + contentValues);
        mContentResolver.insert(Carriers.CONTENT_URI, contentValues);

        // get values in table
        final String[] testProjection =
        {
            Carriers.APN,
            Carriers.NAME,
            Carriers.CURRENT,
        };
        final String selection = Carriers.NUMERIC + "=?";
        String[] selectionArgs = { insertNumeric };
        Log.d(TAG, "testInsertCarriers query projection: " + testProjection
                + "\ntestInsertCarriers selection: " + selection
                + "\ntestInsertCarriers selectionArgs: " + selectionArgs);
        Cursor cursor = mContentResolver.query(Carriers.CONTENT_URI,
                testProjection, selection, selectionArgs, null);

        // verify that inserted values match results of query
        assertNotNull(cursor);
        assertEquals(1, cursor.getCount());
        cursor.moveToFirst();
        final String resultApn = cursor.getString(0);
        final String resultName = cursor.getString(1);
        final Integer resultCurrent = cursor.getInt(2);
        assertEquals(insertApn, resultApn);
        assertEquals(insertName, resultName);
        assertEquals(insertCurrent, resultCurrent);

        // delete test content
        final String selectionToDelete = Carriers.NUMERIC + "=?";
        String[] selectionArgsToDelete = { insertNumeric };
        Log.d(TAG, "testInsertCarriers deleting selection: " + selectionToDelete
                + "testInsertCarriers selectionArgs: " + selectionArgs);
        int numRowsDeleted = mContentResolver.delete(Carriers.CONTENT_URI,
                selectionToDelete, selectionArgsToDelete);
        assertEquals(1, numRowsDeleted);

        // verify that deleted values are gone
        cursor = mContentResolver.query(Carriers.CONTENT_URI,
                testProjection, selection, selectionArgs, null);
        assertEquals(0, cursor.getCount());
    }
