    protected void setUp() throws Exception {
        super.setUp();
        LruCache<String, CachedValue<Integer>> lruCache =
            new LruCache<String, ExpirableCache.CachedValue<Integer>>(20);
        mCache = ExpirableCache.create(lruCache);
    }
