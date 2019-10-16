  public void test_configure() {

    DummyContext context = new DummyContext();

    // Execute configure
    PropertiesBasedServiceRegistryConfiguration config = new PropertiesBasedServiceRegistryConfiguration(
        this._ant4EclipseConfiguration);
    config.configure(context);

    // Make sure only given services have been registered
    Object serviceA = context._registeredServices.remove("ant4eclipse.TestServiceA");
    assertTrue(serviceA instanceof TestServiceAImpl);

    Object serviceB = context._registeredServices.remove("TestServiceB");
    assertTrue(serviceB instanceof TestServiceBImpl);

    assertTrue(context._registeredServices.isEmpty());

  }
