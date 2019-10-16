    private void createTableAndClearCache() {
        mDatabase.disableWriteAheadLogging();
        mDatabase.execSQL("DROP TABLE IF EXISTS " + TEST_TABLE);
        mDatabase.execSQL("CREATE TABLE " + TEST_TABLE + " (i int, j int);");
        mDatabase.enableWriteAheadLogging();
        mDatabase.lock();
        // flush the above statement from cache and close all the pending statements to be released
        mDatabase.deallocCachedSqlStatements();
        mDatabase.closePendingStatements();
        mDatabase.unlock();
        assertEquals(0, mDatabase.getQueuedUpStmtList().size());
    }
