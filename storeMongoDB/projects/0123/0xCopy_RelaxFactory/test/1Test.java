  static public void tearDown() throws Exception {

    try {
      killswitch.set(true);
      getSelector().close();
      exec.shutdown();
    } catch (Exception ignore) {
    }
  }
