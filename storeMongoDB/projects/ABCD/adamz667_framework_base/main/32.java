    public void setMaxSqlCacheSize(int cacheSize) {
        synchronized (this) {
            LruCache<String, SQLiteCompiledSql> oldCompiledQueries = mCompiledQueries;
            if (cacheSize > MAX_SQL_CACHE_SIZE || cacheSize < 0) {
                throw new IllegalStateException(
                        "expected value between 0 and " + MAX_SQL_CACHE_SIZE);
            } else if (oldCompiledQueries != null && cacheSize < oldCompiledQueries.maxSize()) {
                throw new IllegalStateException("cannot set cacheSize to a value less than the "
                        + "value set with previous setMaxSqlCacheSize() call.");
            }
            mCompiledQueries = new LruCache<String, SQLiteCompiledSql>(cacheSize) {
                @Override
                protected void entryRemoved(boolean evicted, String key, SQLiteCompiledSql oldValue,
                        SQLiteCompiledSql newValue) {
                    verifyLockOwner();
                    oldValue.releaseIfNotInUse();
                }
            };
            if (oldCompiledQueries != null) {
                for (Map.Entry<String, SQLiteCompiledSql> entry
                        : oldCompiledQueries.snapshot().entrySet()) {
                    mCompiledQueries.put(entry.getKey(), entry.getValue());
                }
            }
        }
    }
