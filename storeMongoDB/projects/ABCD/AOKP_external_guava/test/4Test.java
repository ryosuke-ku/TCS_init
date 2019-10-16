  public void testElapsedTime_notRunning() {
    ticker.advance(1);
    stopwatch.start();
    ticker.advance(4);
    stopwatch.stop();
    ticker.advance(9);
    assertEquals(4, stopwatch.elapsedTime(NANOSECONDS));
  }
