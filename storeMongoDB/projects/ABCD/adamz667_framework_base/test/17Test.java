    public void testLruCachingOfSqliteCompiledSqlObjs() {
        createTableAndClearCache();
        // set cache size
        int N = SQLiteDatabase.MAX_SQL_CACHE_SIZE;
        mDatabase.setMaxSqlCacheSize(N);

        // do N+1 queries - and when the 0th entry is removed from LRU cache due to the
        // insertion of (N+1)th entry, make sure 0th entry is closed
        ArrayList<Integer> stmtObjs = new ArrayList<Integer>();
        ArrayList<String> sqlStrings = new ArrayList<String>();
        int stmt0 = 0;
        for (int i = 0; i < N+1; i++) {
            String s = "insert into test values(" + i + ",?);";
            sqlStrings.add(s);
            ClassToTestSqlCompilationAndCaching c =
                    ClassToTestSqlCompilationAndCaching.create(mDatabase, s);
            int n = c.getSqlStatementId();
            stmtObjs.add(i, n);
            if (i == 0) {
                // save the statementId of this obj. we want to make sure it is thrown out of
                // the cache at the end of this test.
                stmt0 = n;
            }
            c.close();
        }
        // is 0'th entry out of the cache? it should be in the list of statementIds
        // corresponding to the pre-compiled sql statements to be finalized.
        assertTrue(mDatabase.getQueuedUpStmtList().contains(stmt0));
        for (int i = 1; i < N+1; i++) {
            SQLiteCompiledSql compSql = mDatabase.getCompiledStatementForSql(sqlStrings.get(i));
            assertNotNull(compSql);
            assertTrue(stmtObjs.contains(compSql.nStatement));
        }
    }
