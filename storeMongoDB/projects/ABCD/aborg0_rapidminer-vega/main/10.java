    public RepositoryLocation getRepositoryLocation() {
        if (processLocation instanceof RepositoryProcessLocation) {
            return ((RepositoryProcessLocation) processLocation).getRepositoryLocation();
        } else {
            return null;
        }
    }
