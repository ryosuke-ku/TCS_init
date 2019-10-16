    public void definePropertyDescriptor(PropertyDescriptor<?> propertyDescriptor) throws IllegalArgumentException {
        // Define on the underlying Rule, where it is impossible to have two
        // property descriptors with the same name. Therefore, there is no need
        // to check if the property is already overridden at this level.
        super.definePropertyDescriptor(propertyDescriptor);
        if (propertyDescriptors == null) {
            propertyDescriptors = new ArrayList<>();
        }
        propertyDescriptors.add(propertyDescriptor);
    }
