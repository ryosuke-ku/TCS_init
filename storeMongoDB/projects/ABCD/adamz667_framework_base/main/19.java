    public SQLiteStatement compileStatement(String sql) throws SQLException {
        verifyDbIsOpen();
        return new SQLiteStatement(this, sql, null);
    }
