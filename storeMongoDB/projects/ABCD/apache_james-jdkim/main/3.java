    protected CharSequence getValue(String key) {
        CharSequence val = tagValues.get(key);
        if (val == null)
            return getDefault(key);
        else
            return val;
    }
