    /* package */ synchronized SQLiteCompiledSql getCompiledStatementForSql(String sql) {
        return mCompiledQueries.get(sql);
    }
