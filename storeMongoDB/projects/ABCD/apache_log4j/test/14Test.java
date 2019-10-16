  public void testSetPriority() {
    Logger logger = Logger.getLogger("org.example.foo");
    Priority debug = Level.DEBUG;
    logger.setPriority(debug);
  }
