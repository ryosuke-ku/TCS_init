  public void configure(ConfigurationContext context) {
    Assure.notNull("context", context);

    // get all properties describing a service
    Iterable<Pair<String, String>> serviceProperties = this._ant4EclipseConfiguration.getAllProperties(PROPERTY_PREFIX);

    // Iterate over all service descriptions found
    for (Pair<String, String> serviceProperty : serviceProperties) {

      // instantiate new service instance
      Object serviceInstance = Utilities.newInstance(serviceProperty.getSecond());

      // register new service
      context.registerService(serviceInstance, serviceProperty.getFirst());
    }

  }
