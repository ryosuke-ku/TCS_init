    public void testDbCloseReleasingAllCachedSql() {
        mDatabase.execSQL("CREATE TABLE test (_id INTEGER PRIMARY KEY, text1 TEXT, text2 TEXT, " +
                "num1 INTEGER, num2 INTEGER, image BLOB);");
        final String statement = "DELETE FROM test WHERE _id=?;";
        SQLiteStatement statementDoNotClose = mDatabase.compileStatement(statement);
        statementDoNotClose.bindLong(1, 1);
        /* do not close statementDoNotClose object.
         * That should leave it in SQLiteDatabase.mPrograms.
         * mDatabase.close() in tearDown() should release it.
         */
    }
