  public void testSerialize() throws IOException {
    // make sure we aren't testing defaults
    final BatchWriterConfig bwDefaults = new BatchWriterConfig();
    assertNotEquals(7654321l, bwDefaults.getMaxLatency(TimeUnit.MILLISECONDS));
    assertNotEquals(9898989l, bwDefaults.getTimeout(TimeUnit.MILLISECONDS));
    assertNotEquals(42, bwDefaults.getMaxWriteThreads());
    assertNotEquals(1123581321l, bwDefaults.getMaxMemory());
    assertNotEquals(Durability.FLUSH, bwDefaults.getDurability());

    // test setting all fields
    BatchWriterConfig bwConfig = new BatchWriterConfig();
    bwConfig.setMaxLatency(7654321l, TimeUnit.MILLISECONDS);
    bwConfig.setTimeout(9898989l, TimeUnit.MILLISECONDS);
    bwConfig.setMaxWriteThreads(42);
    bwConfig.setMaxMemory(1123581321l);
    bwConfig.setDurability(Durability.FLUSH);
    byte[] bytes = createBytes(bwConfig);
    checkBytes(bwConfig, bytes);

    // test human-readable serialization
    bwConfig = new BatchWriterConfig();
    bwConfig.setMaxWriteThreads(42);
    bytes = createBytes(bwConfig);
    assertEquals("     i#maxWriteThreads=42", new String(bytes, UTF_8));
    checkBytes(bwConfig, bytes);

    // test human-readable with 2 fields
    bwConfig = new BatchWriterConfig();
    bwConfig.setMaxWriteThreads(24);
    bwConfig.setTimeout(3, TimeUnit.SECONDS);
    bytes = createBytes(bwConfig);
    assertEquals("     v#maxWriteThreads=24,timeout=3000", new String(bytes, UTF_8));
    checkBytes(bwConfig, bytes);

    // test human-readable durability
    bwConfig = new BatchWriterConfig();
    bwConfig.setDurability(Durability.LOG);
    bytes = createBytes(bwConfig);
    assertEquals("     e#durability=LOG", new String(bytes, UTF_8));
  }
