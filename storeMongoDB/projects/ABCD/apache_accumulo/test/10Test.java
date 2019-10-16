  private byte[] createBytes(BatchWriterConfig bwConfig) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    bwConfig.write(new DataOutputStream(baos));
    return baos.toByteArray();
  }
