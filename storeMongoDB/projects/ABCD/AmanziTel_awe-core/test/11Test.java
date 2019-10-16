    public void testCheckActivityOnCreateLoader() throws Exception {
        ISaver<IConfiguration, IData>[] savers = new ISaver[SAVER_IDS.length];
        IConfigurationElement loaderElement = createSaverElement(SAVER_IDS);

        for (int i = 0; i < SAVER_IDS.length; i++) {
            ISaver<IConfiguration, IData> saver = mock(ISaver.class);

            doReturn(saver).when(context).createSaver(SAVER_IDS[i]);
            savers[i] = saver;
        }

        IParser<IConfiguration, IData> parser = mock(IParser.class);
        doReturn(parser).when(context).createParser(PARSER_ID);

        IValidator<IConfiguration> validator = mock(IValidator.class);
        doReturn(validator).when(context).createValidator(VALIDATOR_ID);

        ILoader<IConfiguration, IData> loader = mock(ILoader.class);
        doReturn(loader).when(context).createLoader();

        doReturn(loaderElement).when(context).findConfgiruationElement(LoaderContext.LOADER_EXTENSION_ID, LOADER_ID);

        context.createLoader(LOADER_ID);

        verify(loaderElement).getAttribute(LoaderContext.VALIDATOR_ATTRIBUTE);
        verify(loaderElement).getAttribute(LoaderContext.PARSER_ATTRIBUTE);

        verify(loader).setParser(parser);
        verify(loader).setValidator(validator);

        verify(loaderElement).getChildren(LoaderContext.SAVER_CHILDREN);

        for (int i = 0; i < SAVER_IDS.length; i++) {
            verify(context).createSaver(SAVER_IDS[i]);

            verify(loader).addSaver(savers[i]);
        }
    }
