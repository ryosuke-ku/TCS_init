  public static ChunkImpl read(DataInput in) throws IOException {
    ChunkImpl w = new ChunkImpl();
    w.readFields(in);
    return w;
  }
