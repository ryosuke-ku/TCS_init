    public ConcurrentMap<String, String> getTestMap() {
        if (null == fieldTestMap) {
            fieldTestMap = new ConcurrentHashMap<String, String>();
        }
        return fieldTestMap;
    }
