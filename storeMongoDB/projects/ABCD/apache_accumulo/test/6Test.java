  public void testTinyTimeConversions() {
    BatchWriterConfig bwConfig = new BatchWriterConfig();
    bwConfig.setMaxLatency(999, TimeUnit.MICROSECONDS);
    bwConfig.setTimeout(999, TimeUnit.MICROSECONDS);

    assertEquals(1000, bwConfig.getMaxLatency(TimeUnit.MICROSECONDS));
    assertEquals(1000, bwConfig.getTimeout(TimeUnit.MICROSECONDS));
    assertEquals(1, bwConfig.getMaxLatency(TimeUnit.MILLISECONDS));
    assertEquals(1, bwConfig.getTimeout(TimeUnit.MILLISECONDS));

    bwConfig.setMaxLatency(10, TimeUnit.NANOSECONDS);
    bwConfig.setTimeout(10, TimeUnit.NANOSECONDS);

    assertEquals(1000000, bwConfig.getMaxLatency(TimeUnit.NANOSECONDS));
    assertEquals(1000000, bwConfig.getTimeout(TimeUnit.NANOSECONDS));
    assertEquals(1, bwConfig.getMaxLatency(TimeUnit.MILLISECONDS));
    assertEquals(1, bwConfig.getTimeout(TimeUnit.MILLISECONDS));

  }
