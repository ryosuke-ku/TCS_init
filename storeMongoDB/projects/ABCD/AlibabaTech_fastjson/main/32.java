    public int getIntValue(int index) {
        Object value = get(index);

        if (value == null) {
            return 0;
        }

        return castToInt(value).intValue();
    }
