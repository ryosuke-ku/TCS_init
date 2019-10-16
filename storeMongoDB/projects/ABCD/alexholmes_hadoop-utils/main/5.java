    public Integer getStartKey() {
        String val = config.get(START_KEY, null);
        if (val != null) {
            return Integer.valueOf(val);
        }
        return null;
    }
