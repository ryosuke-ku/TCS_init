  public void testElapsedMillis_multipleSegments() {
    stopwatch.start();
    ticker.advance(9000000);
    stopwatch.stop();

    ticker.advance(16000000);

    stopwatch.start();
    assertEquals(9, stopwatch.elapsedMillis());
    ticker.advance(25000000);
    assertEquals(34, stopwatch.elapsedMillis());

    stopwatch.stop();
    ticker.advance(36000000);
    assertEquals(34, stopwatch.elapsedMillis());
  }
