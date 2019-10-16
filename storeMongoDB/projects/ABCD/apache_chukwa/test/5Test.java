  public void testVersion() {
    ChunkBuilder cb = new ChunkBuilder();
    cb.addRecord("foo".getBytes());
    cb.addRecord("bar".getBytes());
    cb.addRecord("baz".getBytes());
    Chunk c = cb.getChunk();
    DataOutputBuffer ob = new DataOutputBuffer(c.getSerializedSizeEstimate());
    try {
      c.write(ob);
      DataInputBuffer ib = new DataInputBuffer();
      ib.reset(ob.getData(), c.getSerializedSizeEstimate());
      int version = ib.readInt();
      ib.close();
      assertEquals(version, ChunkImpl.PROTOCOL_VERSION);
    } catch (IOException e) {
      e.printStackTrace();
      fail("Should nor raise any exception");
    }
  }
