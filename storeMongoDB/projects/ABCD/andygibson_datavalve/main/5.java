    public T get(int index) {
        Object value = values.get(index);
        if (value == null) {
            value = loadValue(index);
        }
        if (value == NULL_HOLDER) {
            return null;
        }
        return values.get(index);
    }
