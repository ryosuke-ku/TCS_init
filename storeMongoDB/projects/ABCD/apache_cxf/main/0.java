    public URI getContractLocation(QName qname) {
        for (ServiceContractResolver resolver : resolvers) {
            URI contractLocation = resolver.getContractLocation(qname);
            if (null != contractLocation) {
                return contractLocation;
            }
        }
        return null;
    }
