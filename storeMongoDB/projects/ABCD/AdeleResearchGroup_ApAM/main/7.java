    public MethodMetadata[] getMethods(String name) {
        List<MethodMetadata> list = new ArrayList<MethodMetadata>();
        for (MethodMetadata metadata : m_methods) {
            if (metadata.getMethodName().equalsIgnoreCase(name)) {
                list.add(metadata);
            }
        }
        return list.toArray(new MethodMetadata[list.size()]);
    }
