    public NodeDefinition getDefinition() throws RepositoryException {
        // check state of this instance
        sanityCheck();

        return data.getNodeDefinition();
    }
