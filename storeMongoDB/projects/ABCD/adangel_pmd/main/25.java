    public <T> void setProperty(PropertyDescriptor<T> propertyDescriptor, T value) {
        // Only override if different than current value.
        if (!isSame(super.getProperty(propertyDescriptor), value)) {
            if (propertyValues == null) {
                propertyValues = new HashMap<>();
            }
            propertyValues.put(propertyDescriptor, value);
            super.setProperty(propertyDescriptor, value);
        }
    }
