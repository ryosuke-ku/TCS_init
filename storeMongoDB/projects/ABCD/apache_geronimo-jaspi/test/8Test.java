    public void testRegisterUnregister() throws Exception {
        AuthConfigFactory factory = AuthConfigFactory.getFactory();
        String regId = factory.registerConfigProvider(DummyProvider.class.getName(), null, "layer3", "appContext3", "description");
        assertNotNull(regId);
        RegistrationContext regContext = factory.getRegistrationContext(regId);
        assertNotNull(regContext);
        assertEquals("layer3", regContext.getMessageLayer());
        assertEquals("appContext3", regContext.getAppContext());
        assertEquals("description", regContext.getDescription());

        assertTrue(factory.removeRegistration(regId));

        regContext = factory.getRegistrationContext(regId);
        assertNull(regContext);
    }
