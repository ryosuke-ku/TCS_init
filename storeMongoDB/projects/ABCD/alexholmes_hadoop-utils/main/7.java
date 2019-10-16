    public Integer getEndKey() {
        String val = config.get(END_KEY, null);
        if (val != null) {
            return Integer.valueOf(val);
        }
        return null;
    }
