    protected Node getParent(final Node rootNode) throws ServiceException {
        return nodeService.getParent(rootNode, getRelationToParent());
    }
