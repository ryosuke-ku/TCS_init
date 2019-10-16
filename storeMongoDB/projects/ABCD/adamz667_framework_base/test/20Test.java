    public void testTransactionAndWalInterplay1() throws InterruptedException {
        createTableAndClearCache();
        mDatabase.execSQL("INSERT into " + TEST_TABLE + " values(10, 999);");
        String sql = "select * from " + TEST_TABLE;
        Cursor c = mDatabase.rawQuery(sql, null);
        // should have 1 row in the table
        assertEquals(1, c.getCount());
        mDatabase.beginTransactionNonExclusive();
        try {
            mDatabase.execSQL("INSERT into " + TEST_TABLE + " values(100, 9909);");
            assertEquals(2, DatabaseUtils.longForQuery(mDatabase,
                    "select count(*) from " + TEST_TABLE, null));
            // requery on the previously opened cursor
            // cursor should now use the main database connection and see 2 rows
            c.requery();
            assertEquals(2, c.getCount());
            mDatabase.setTransactionSuccessful();
        } finally {
            mDatabase.endTransaction();
        }
        c.close();

        // do the same test but now do the requery in a separate thread.
        createTableAndClearCache();
        mDatabase.execSQL("INSERT into " + TEST_TABLE + " values(10, 999);");
        final Cursor c1 = mDatabase.rawQuery("select count(*) from " + TEST_TABLE, null);
        // should have 1 row in the table
        assertEquals(1, c1.getCount());
        mDatabase.beginTransactionNonExclusive();
        try {
            mDatabase.execSQL("INSERT into " + TEST_TABLE + " values(100, 9909);");
            assertEquals(2, DatabaseUtils.longForQuery(mDatabase,
                    "select count(*) from " + TEST_TABLE, null));
            // query in a different thread. that causes the cursor to use a pooled connection
            // and since this thread hasn't committed its changes, the cursor should still see only
            // 1 row
            Thread t = new Thread() {
                @Override public void run() {
                    c1.requery();
                    assertEquals(1, c1.getCount());
                }
            };
            t.start();
            t.join();
            // should be 2 rows now - including the the row inserted above
            mDatabase.setTransactionSuccessful();
        } finally {
            mDatabase.endTransaction();
        }
        c1.close();
    }
