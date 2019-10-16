    public void testCursorsWithClosedDbConnAfterDisableWriteAheadLogging() {
        mDatabase.disableWriteAheadLogging();
        mDatabase.beginTransactionNonExclusive();
        mDatabase.execSQL("create table test (i int);");
        mDatabase.execSQL("insert into test values(1);");
        mDatabase.setTransactionSuccessful();
        mDatabase.endTransaction();
        mDatabase.enableWriteAheadLogging();
        assertNotNull(mDatabase.mConnectionPool);
        assertEquals(0, mDatabase.mConnectionPool.getSize());
        assertEquals(0, mDatabase.mConnectionPool.getFreePoolSize());
        // get a cursor which should use pooled database connection
        Cursor c = mDatabase.rawQuery("select * from test", null);
        assertEquals(1, c.getCount());
        assertEquals(1, mDatabase.mConnectionPool.getSize());
        assertEquals(1, mDatabase.mConnectionPool.getFreePoolSize());
        SQLiteDatabase db = mDatabase.mConnectionPool.getConnectionList().get(0);
        assertTrue(mDatabase.mConnectionPool.isDatabaseObjFree(db));
        // disable WAL - which should close connection pool and all pooled connections
        mDatabase.disableWriteAheadLogging();
        assertNull(mDatabase.mConnectionPool);
        assertFalse(db.isOpen());
        // cursor data should still be accessible because it is fetching data from CursorWindow
        c.moveToNext();
        assertEquals(1, c.getInt(0));
        c.requery();
        assertEquals(1, c.getCount());
        c.moveToNext();
        assertEquals(1, c.getInt(0));
        c.close();
    }
