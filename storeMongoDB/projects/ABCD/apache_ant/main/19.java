    public List<Method> getExtensionPoints() {
        return addTypeMethods.isEmpty()
                ? Collections.<Method> emptyList() : Collections.unmodifiableList(addTypeMethods);
    }
