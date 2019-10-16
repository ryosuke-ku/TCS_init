    private void runTestForStartXactBeforeUpdateSql(int stmtType) throws InterruptedException {
        createTableAndClearCache();

        ContentValues values = new ContentValues();
        // make some changes to data in TEST_TABLE
        for (int i = 0; i < 5; i++) {
            values.put("i", i);
            values.put("j", "i" + System.currentTimeMillis());
            mDatabase.insert(TEST_TABLE, null, values);
            switch (stmtType) {
                case UPDATE:
                    values.put("j", "u" + System.currentTimeMillis());
                    mDatabase.update(TEST_TABLE, values, "i = " + i, null);
                    break;
                case DELETE:
                    mDatabase.delete(TEST_TABLE, "i = 1", null);
                    break;
            }
        }
        // do a query. even though query uses a different database connection,
        // it should still see the above changes to data because the above standalone
        // insert/update/deletes are done in transactions automatically.
        String sql = "select count(*) from " + TEST_TABLE;
        SQLiteStatement stmt = mDatabase.compileStatement(sql);
        final int expectedValue = (stmtType == DELETE) ? 4 : 5;
        assertEquals(expectedValue, stmt.simpleQueryForLong());
        stmt.close();
        Cursor c = mDatabase.rawQuery(sql, null);
        assertEquals(1, c.getCount());
        c.moveToFirst();
        assertEquals(expectedValue, c.getLong(0));
        c.close();

        // do 5 more changes in a transaction but do a query before and after the commit
        mDatabase.beginTransaction();
        for (int i = 10; i < 15; i++) {
            values.put("i", i);
            values.put("j", "i" + System.currentTimeMillis());
            mDatabase.insert(TEST_TABLE, null, values);
            switch (stmtType) {
                case UPDATE:
                    values.put("j", "u" + System.currentTimeMillis());
                    mDatabase.update(TEST_TABLE, values, "i = " + i, null);
                    break;
                case DELETE:
                    mDatabase.delete(TEST_TABLE, "i = 1", null);
                    break;
            }
        }
        mDatabase.setTransactionSuccessful();
        // do a query before commit - should still have 5 rows
        // this query should run in a different thread to force it to use a different database
        // connection
        Thread t = new Thread() {
            @Override public void run() {
                String sql = "select count(*) from " + TEST_TABLE;
                SQLiteStatement stmt = getDb().compileStatement(sql);
                assertEquals(expectedValue, stmt.simpleQueryForLong());
                stmt.close();
                Cursor c = getDb().rawQuery(sql, null);
                assertEquals(1, c.getCount());
                c.moveToFirst();
                assertEquals(expectedValue, c.getLong(0));
                c.close();
            }
        };
        t.start();
        // wait until the above thread is done
        t.join();
        // commit and then query. should see changes from the transaction
        mDatabase.endTransaction();
        stmt = mDatabase.compileStatement(sql);
        final int expectedValue2 = (stmtType == DELETE) ? 9 : 10;
        assertEquals(expectedValue2, stmt.simpleQueryForLong());
        stmt.close();
        c = mDatabase.rawQuery(sql, null);
        assertEquals(1, c.getCount());
        c.moveToFirst();
        assertEquals(expectedValue2, c.getLong(0));
        c.close();
    }
