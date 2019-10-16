    public long getLongValue(int index) {
        Object value = get(index);

        if (value == null) {
            return 0L;
        }

        return castToLong(value).longValue();
    }
