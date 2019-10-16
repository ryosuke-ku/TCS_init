  public void testStop() {
    stopwatch.start();
    assertSame(stopwatch, stopwatch.stop());
    assertFalse(stopwatch.isRunning());
  }
