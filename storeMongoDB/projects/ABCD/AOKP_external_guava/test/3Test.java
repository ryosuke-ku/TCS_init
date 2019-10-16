  public void testReset_whileRunning() {
    ticker.advance(1);
    stopwatch.start();
    assertEquals(0, stopwatch.elapsedTime(NANOSECONDS));
    ticker.advance(2);
    assertEquals(2, stopwatch.elapsedTime(NANOSECONDS));
    stopwatch.reset();
    assertFalse(stopwatch.isRunning());
    ticker.advance(3);
    assertEquals(0, stopwatch.elapsedTime(NANOSECONDS));
  }
