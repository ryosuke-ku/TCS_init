    public static <K, V> ExpirableCache<K, V> create(int maxSize) {
        return create(new LruCache<K, CachedValue<V>>(maxSize));
    }
