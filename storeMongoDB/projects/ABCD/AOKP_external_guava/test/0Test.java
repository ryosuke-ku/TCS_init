  public void testStop_new() {
    try {
      stopwatch.stop();
      fail();
    } catch (IllegalStateException expected) {
    }
    assertFalse(stopwatch.isRunning());
  }
