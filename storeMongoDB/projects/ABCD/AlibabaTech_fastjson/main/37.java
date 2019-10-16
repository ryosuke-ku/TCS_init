    public Double getDouble(int index) {
        Object value = get(index);

        return castToDouble(value);
    }
