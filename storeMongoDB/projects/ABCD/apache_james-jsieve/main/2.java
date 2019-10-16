    public ConcurrentMap<String, String> getComparatorMap() {
        if (null == fieldComparatorMap) {
            fieldComparatorMap = new ConcurrentHashMap<String, String>();
        }
        return fieldComparatorMap;
    }
