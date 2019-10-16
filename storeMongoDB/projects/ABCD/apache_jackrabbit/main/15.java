    public Node addNodeWithUuid(
            String relPath, String nodeTypeName, String uuid)
            throws RepositoryException {
        return perform(new AddNodeOperation(this, relPath, nodeTypeName, uuid));
    }
