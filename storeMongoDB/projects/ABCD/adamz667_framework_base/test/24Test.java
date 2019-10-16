    public void testStatementClose() {
        createTableAndClearCache();
        // fill up statement cache in mDatabase
        int N = SQLiteDatabase.MAX_SQL_CACHE_SIZE;
        mDatabase.setMaxSqlCacheSize(N);
        SQLiteStatement stmt;
        int stmt0Id = 0;
        for (int i = 0; i < N; i ++) {
            ClassToTestSqlCompilationAndCaching c =
                    ClassToTestSqlCompilationAndCaching.create(mDatabase,
                            "insert into test values(" + i + ", ?);");
            // keep track of 0th entry
            if (i == 0) {
                stmt0Id = c.getSqlStatementId();
            }
            c.close();
        }

        // add one more to the cache - and the above 'stmt0Id' should fall out of cache
        ClassToTestSqlCompilationAndCaching stmt1 =
                ClassToTestSqlCompilationAndCaching.create(mDatabase,
                        "insert into test values(100, ?);");
        stmt1.close();

        // the above close() should have queuedUp the statement for finalization
        ArrayList<Integer> statementIds = mDatabase.getQueuedUpStmtList();
        assertTrue(statementIds.contains(stmt0Id));

        // execute something to see if this statement gets finalized
        mDatabase.execSQL("delete from test where i = 10;");
        statementIds = mDatabase.getQueuedUpStmtList();
        assertFalse(statementIds.contains(stmt0Id));
    }
