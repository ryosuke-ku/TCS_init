    public String getProperty(final String propertyName) {
        final Object value = PropertyHelper.getPropertyHelper(this).getProperty(propertyName);
        return value == null ? null : String.valueOf(value);
    }
