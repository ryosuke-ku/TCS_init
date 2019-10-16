    public EndpointReferenceType mint(EndpointReferenceType physical) {
        EndpointReferenceType logical = null;
        for (EndpointResolver resolver : resolvers) {
            logical = resolver.mint(physical);
            if (logical != null) {
                break;
            }
        }
        return logical;        
    }
