    public short getShortValue(int index) {
        Object value = get(index);

        if (value == null) {
            return 0;
        }

        return castToShort(value).shortValue();
    }
