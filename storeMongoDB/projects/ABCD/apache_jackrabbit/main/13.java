    public Node addNode(String relPath, String nodeTypeName)
            throws RepositoryException {
        return addNodeWithUuid(relPath, nodeTypeName, null);
    }
