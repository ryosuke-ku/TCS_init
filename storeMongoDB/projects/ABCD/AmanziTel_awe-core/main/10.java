    protected <T extends Object> T createElement(final String extensionPoint, final String id) throws CoreException {
        IConfigurationElement element = findConfgiruationElement(extensionPoint, id);

        if (element != null) {
            return (T)element.createExecutableExtension(CLASS_ATTRIBUTE);
        }

        return null;
    }
