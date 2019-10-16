    public MethodMetadata getConstructor(String[] types) {
    	return getMethod("$init", types); // Constructors are named $init in the manipulation metadata
    }
