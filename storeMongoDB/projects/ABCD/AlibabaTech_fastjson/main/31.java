    public Integer getInteger(int index) {
        Object value = get(index);

        return castToInt(value);
    }
