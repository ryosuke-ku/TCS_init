    protected Value createValue(@Nullable Object v) throws RepositoryException {
        if (v == null) {
            return null;
        } else if (v instanceof Boolean) {
            return valueFactory.createValue((Boolean) v);
        } else if (v instanceof Byte || v instanceof Short || v instanceof Integer || v instanceof Long) {
            return valueFactory.createValue(((Number) v).longValue());
        } else if (v instanceof Float || v instanceof Double) {
            return valueFactory.createValue(((Number) v).doubleValue());
        } else if (v instanceof BigDecimal) {
            return valueFactory.createValue((BigDecimal) v);
        } else if (v instanceof Calendar) {
            return valueFactory.createValue((Calendar) v);
        } else if (v instanceof Date) {
            Calendar cal = Calendar.getInstance();
            cal.setTime((Date) v);
            return valueFactory.createValue(cal);
        } else if (v instanceof byte[]) {
            Binary bin = valueFactory.createBinary(new ByteArrayInputStream((byte[])v));
            return valueFactory.createValue(bin);
        } else if (v instanceof Binary) {
            return valueFactory.createValue((Binary) v);
        } else if (v instanceof InputStream) {
            return valueFactory.createValue((InputStream) v);
        } else if (v instanceof char[]) {
            return valueFactory.createValue(new String((char[]) v));
        } else {
            return valueFactory.createValue(String.valueOf(v));
        }
    }
