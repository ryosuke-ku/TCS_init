    public static <K,V> Map.Entry<K, V> get(final Map<K,V> map, final int index) {
        checkIndexBounds(index);
        return get(map.entrySet(), index);
    }
