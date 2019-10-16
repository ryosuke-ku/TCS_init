    protected IConfigurationElement findConfgiruationElement(final String extensionPoint, final String id) {
        IConfigurationElement result = null;

        for (IConfigurationElement element : registry.getConfigurationElementsFor(extensionPoint)) {
            if (id.equals(element.getAttribute(ID_ATTRIBUTE))) {
                result = element;
                break;
            }
        }

        return result;
    }
