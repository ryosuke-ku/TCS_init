  public void write(DataOutput out) throws IOException {
    // write this out in a human-readable way
    ArrayList<String> fields = new ArrayList<>();
    if (maxMemory != null)
      addField(fields, "maxMemory", maxMemory);
    if (maxLatency != null)
      addField(fields, "maxLatency", maxLatency);
    if (maxWriteThreads != null)
      addField(fields, "maxWriteThreads", maxWriteThreads);
    if (timeout != null)
      addField(fields, "timeout", timeout);
    if (durability != Durability.DEFAULT)
      addField(fields, "durability", durability);
    String output = StringUtils.join(",", fields);

    byte[] bytes = output.getBytes(UTF_8);
    byte[] len = String.format("%6s#", Integer.toString(bytes.length, 36)).getBytes(UTF_8);
    if (len.length != 7)
      throw new IllegalStateException("encoded length does not match expected value");
    out.write(len);
    out.write(bytes);
  }
