    public void setUp() {
        handler = new DefaultExceptionHandler();
        Modules modules = mock(Modules.class);
        when(modules.getExceptionMappers()).thenReturn(
                Arrays.<ExceptionMapper> asList(new UnsupportedMediaTypeExceptionMapper(),
                        new InvalidRequestFormatExceptionMapper(),
                        new RequestMethodUnsupportedExceptionMapper()));
        handler.setModules(modules);
    }
