  public void testForcedLog() {
    MockCategory category = new MockCategory("org.example.foo");
    category.setAdditivity(false);
    category.addAppender(new VectorAppender());
    category.info("Hello, World");
  }
