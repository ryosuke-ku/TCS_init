    public Long getLong(int index) {
        Object value = get(index);

        return castToLong(value);
    }
