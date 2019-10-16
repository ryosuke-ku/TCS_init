    public void testDisableWriteAheadLogging() {
        mDatabase.execSQL("create table test (i int);");
        mDatabase.enableWriteAheadLogging();
        assertNotNull(mDatabase.mConnectionPool);
        // get a pooled database connection
        SQLiteDatabase db = mDatabase.getDbConnection("select * from test");
        assertNotNull(db);
        assertFalse(mDatabase.equals(db));
        assertTrue(db.isOpen());
        // disable WAL - which should close connection pool and all pooled connections
        mDatabase.disableWriteAheadLogging();
        assertNull(mDatabase.mConnectionPool);
        assertFalse(db.isOpen());
    }
