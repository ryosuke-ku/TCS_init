    public ConcurrentMap<String, String> getCommandMap() {
        if (null == fieldCommandMap) {
            fieldCommandMap = new ConcurrentHashMap<String, String>();
        }
        return fieldCommandMap;
    }
