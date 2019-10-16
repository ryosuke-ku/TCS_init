  public static VaadletsBuilder build(final Reader aReader) {
    final VaadletsBuilder builder = new VaadletsBuilder();
    builder.create(JAXBUtils.unmarshal(aReader).getRootComponent());
    return builder;
  }
