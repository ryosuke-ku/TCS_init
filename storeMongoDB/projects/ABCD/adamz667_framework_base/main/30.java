    /* package */ synchronized void deallocCachedSqlStatements() {
        for (SQLiteCompiledSql compiledSql : mCompiledQueries.snapshot().values()) {
            compiledSql.releaseSqlStatement();
        }
        mCompiledQueries.evictAll();
    }
