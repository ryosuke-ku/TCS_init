    public MethodMetadata getMethod(String name, String[] types) {
        for (MethodMetadata metadata : m_methods) {
            if (metadata.getMethodName().equalsIgnoreCase(name) && metadata.getMethodArguments().length == types.length) {
                int argIndex = 0;
                for (; argIndex < types.length; argIndex++) {
                    if (! types[argIndex].equals(metadata.getMethodArguments()[argIndex])) {
                        break;
                    }
                }
                if (argIndex == types.length) { return metadata; } // No mismatch detected.
            }
        }
        return null;
    }
