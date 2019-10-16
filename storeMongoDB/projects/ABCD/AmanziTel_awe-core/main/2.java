    public void initialize(final Node rootNode) throws ModelException {
        assert rootNode != null;

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(getStartLogStatement(INITIALIZE_METHOD_NAME, rootNode));
        }

        try {
            this.rootNode = rootNode;
            name = nodeService.getNodeName(rootNode);
            nodeType = nodeService.getNodeType(rootNode);
            parentNode = getParent(rootNode);
        } catch (final Exception e) {
            processException("An error occured on Model Initialization", e);
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(getFinishLogStatement(INITIALIZE_METHOD_NAME));
        }
    }
