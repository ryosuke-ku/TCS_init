  public void testReasonableDefaults() {
    long expectedMaxMemory = 50 * 1024 * 1024l;
    long expectedMaxLatency = 120000l;
    long expectedTimeout = Long.MAX_VALUE;
    int expectedMaxWriteThreads = 3;
    Durability expectedDurability = Durability.DEFAULT;

    BatchWriterConfig defaults = new BatchWriterConfig();
    assertEquals(expectedMaxMemory, defaults.getMaxMemory());
    assertEquals(expectedMaxLatency, defaults.getMaxLatency(TimeUnit.MILLISECONDS));
    assertEquals(expectedTimeout, defaults.getTimeout(TimeUnit.MILLISECONDS));
    assertEquals(expectedMaxWriteThreads, defaults.getMaxWriteThreads());
    assertEquals(expectedDurability, defaults.getDurability());
  }
