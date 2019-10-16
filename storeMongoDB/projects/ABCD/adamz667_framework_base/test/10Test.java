    private void runExecSqlToStartAndEndTransaction(String str) throws InterruptedException {
        createTableAndClearCache();
        // disable WAL just so queries and updates use the same database connection
        mDatabase.disableWriteAheadLogging();
        mDatabase.execSQL("BEGIN transaction");
        // even though mDatabase.beginTransaction() is not called to start transaction,
        // mDatabase connection should now be in transaction as a result of
        // mDatabase.execSQL("BEGIN transaction")
        // but mDatabase.mLock should not be held by any thread
        assertTrue(mDatabase.inTransaction());
        assertFalse(mDatabase.isDbLockedByCurrentThread());
        assertFalse(mDatabase.isDbLockedByOtherThreads());
        assertTrue(mDatabase.amIInTransaction());
        mDatabase.execSQL("INSERT into " + TEST_TABLE + " values(10, 999);");
        assertTrue(mDatabase.inTransaction());
        assertFalse(mDatabase.isDbLockedByCurrentThread());
        assertFalse(mDatabase.isDbLockedByOtherThreads());
        assertTrue(mDatabase.amIInTransaction());
        Thread t = new Thread() {
            @Override public void run() {
                assertTrue(mDatabase.amIInTransaction());
                assertEquals(999, DatabaseUtils.longForQuery(getDb(),
                        "select j from " + TEST_TABLE + " WHERE i = 10", null));
                assertTrue(getDb().inTransaction());
                assertFalse(getDb().isDbLockedByCurrentThread());
                assertFalse(getDb().isDbLockedByOtherThreads());
                assertTrue(mDatabase.amIInTransaction());
            }
        };
        t.start();
        t.join();
        assertTrue(mDatabase.amIInTransaction());
        assertTrue(mDatabase.inTransaction());
        assertFalse(mDatabase.isDbLockedByCurrentThread());
        assertFalse(mDatabase.isDbLockedByOtherThreads());
        mDatabase.execSQL(str);
        assertFalse(mDatabase.amIInTransaction());
        assertFalse(mDatabase.inTransaction());
        assertFalse(mDatabase.isDbLockedByCurrentThread());
        assertFalse(mDatabase.isDbLockedByOtherThreads());
    }
