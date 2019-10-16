    public void testCheckActivityOnCreateValidator() throws Exception {
        IValidator<IConfiguration> validator = mock(IValidator.class);
        doReturn(validator).when(context).createElement(LoaderContext.VALIDATOR_EXTENSION_ID, VALIDATOR_ID);

        context.createValidator(VALIDATOR_ID);

        verify(context).createElement(LoaderContext.VALIDATOR_EXTENSION_ID, VALIDATOR_ID);
    }
