  public void testToString() {
    stopwatch.start();
    assertEquals("0.000 ns", stopwatch.toString());
    ticker.advance(1);
    assertEquals("1.000 ns", stopwatch.toString());
    ticker.advance(998);
    assertEquals("999.0 ns", stopwatch.toString());
    ticker.advance(1);
    assertEquals("1.000 \u03bcs", stopwatch.toString());
    ticker.advance(1);
    assertEquals("1.001 \u03bcs", stopwatch.toString());
    ticker.advance(8998);
    assertEquals("9.999 \u03bcs", stopwatch.toString());
    stopwatch.reset();
    stopwatch.start();
    ticker.advance(1234567);
    assertEquals("1.235 ms", stopwatch.toString());
    stopwatch.reset();
    stopwatch.start();
    ticker.advance(5000000000L);
    assertEquals("5.000 s", stopwatch.toString());
  }
