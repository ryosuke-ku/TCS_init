    public BigDecimal getBigDecimal(int index) {
        Object value = get(index);

        return castToBigDecimal(value);
    }
