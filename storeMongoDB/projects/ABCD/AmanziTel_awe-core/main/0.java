    public void initialize(final Node parentNode, final String name) throws ModelException {
        assert name != null;

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(getStartLogStatement(INITIALIZE_METHOD_NAME, name));
        }

        initialize(parentNode, name, getModelType());

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(getFinishLogStatement(INITIALIZE_METHOD_NAME));
        }
    }
