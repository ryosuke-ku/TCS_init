  public void testL7dlog() {
    Logger logger = Logger.getLogger("org.example.foo");
    logger.setLevel(Level.ERROR);
    Priority debug = Level.DEBUG;
    logger.l7dlog(debug, "Hello, World", null);
  }
