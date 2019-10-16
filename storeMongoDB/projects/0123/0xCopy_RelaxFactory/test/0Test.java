  public static void tearDown() throws Exception {
    try {
      AsyncSingletonServer.killswitch.set(true);
      AsioVisitor.Helper.getSelector().close();
      exec.shutdown();
    } catch (Exception ignore) {
    }
  }
