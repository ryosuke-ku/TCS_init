    public void copy() throws RepositoryException {
        logger.info(
                "Copying repository content from {} to {}",
                source.getRepository().repConfig.getHomeDir(),
                target.getRepository().repConfig.getHomeDir());
        try {
            copyNamespaces();
            copyNodeTypes();
            copyVersionStore();
            copyWorkspaces();
        } catch (Exception e) {
            throw new RepositoryException("Failed to copy content", e);
        }
    }
