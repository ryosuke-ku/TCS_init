  public void testManualEquality() {
    BatchWriterConfig cfg1 = new BatchWriterConfig(), cfg2 = new BatchWriterConfig();
    cfg1.setMaxLatency(10, TimeUnit.SECONDS);
    cfg2.setMaxLatency(10000, TimeUnit.MILLISECONDS);

    cfg1.setMaxMemory(100);
    cfg2.setMaxMemory(100);

    cfg1.setTimeout(10, TimeUnit.SECONDS);
    cfg2.setTimeout(10000, TimeUnit.MILLISECONDS);

    assertEquals(cfg1, cfg2);

    assertEquals(cfg1.hashCode(), cfg2.hashCode());
  }
