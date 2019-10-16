  public void readFields(DataInput in) throws IOException {
    byte[] len = new byte[7];
    in.readFully(len);
    String strLen = new String(len, UTF_8);
    if (!strLen.endsWith("#"))
      throw new IllegalStateException("length was not encoded correctly");
    byte[] bytes = new byte[Integer.parseInt(strLen.substring(strLen.lastIndexOf(' ') + 1, strLen.length() - 1), 36)];
    in.readFully(bytes);

    String strFields = new String(bytes, UTF_8);
    String[] fields = StringUtils.split(strFields, '\\', ',');
    for (String field : fields) {
      String[] keyValue = StringUtils.split(field, '\\', '=');
      String key = keyValue[0];
      String value = keyValue[1];
      if ("maxMemory".equals(key)) {
        maxMemory = Long.valueOf(value);
      } else if ("maxLatency".equals(key)) {
        maxLatency = Long.valueOf(value);
      } else if ("maxWriteThreads".equals(key)) {
        maxWriteThreads = Integer.valueOf(value);
      } else if ("timeout".equals(key)) {
        timeout = Long.valueOf(value);
      } else if ("durability".equals(key)) {
        durability = DurabilityImpl.fromString(value);
      } else {
        /* ignore any other properties */
      }
    }
  }
