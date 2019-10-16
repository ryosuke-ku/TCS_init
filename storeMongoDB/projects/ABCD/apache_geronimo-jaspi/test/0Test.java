    public void testWrapClientAuthModule() throws Exception {
        AuthConfigFactory factory = AuthConfigFactory.getFactory();
        AuthModuleType<ClientAuthModule> authModuleType = new AuthModuleType<ClientAuthModule>();
        authModuleType.setClassName(DummyClientAuthModule.class.getName());
        AuthConfigProvider authConfigProvider = JaspiUtil.wrapClientAuthModule("layer", "appContext1", "id", authModuleType, true);
        String regId = factory.registerConfigProvider(authConfigProvider, "layer", "appContext1", "description");
        DummyListener listener = new DummyListener();
        assertNotNull(factory.getConfigProvider("layer", "appContext1", listener));
        factory.removeRegistration(regId);
        assertTrue(listener.notified);
    }
