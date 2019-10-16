    public float getFloatValue(int index) {
        Object value = get(index);

        if (value == null) {
            return 0F;
        }

        return castToFloat(value).floatValue();
    }
