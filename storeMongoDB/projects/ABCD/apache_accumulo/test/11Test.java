  private void checkBytes(BatchWriterConfig bwConfig, byte[] bytes) throws IOException {
    ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
    BatchWriterConfig createdConfig = new BatchWriterConfig();
    createdConfig.readFields(new DataInputStream(bais));

    assertEquals(bwConfig.getMaxMemory(), createdConfig.getMaxMemory());
    assertEquals(bwConfig.getMaxLatency(TimeUnit.MILLISECONDS), createdConfig.getMaxLatency(TimeUnit.MILLISECONDS));
    assertEquals(bwConfig.getTimeout(TimeUnit.MILLISECONDS), createdConfig.getTimeout(TimeUnit.MILLISECONDS));
    assertEquals(bwConfig.getMaxWriteThreads(), createdConfig.getMaxWriteThreads());
  }
