  public void testJsonSerialization() throws IOException {
    EdgeData edd = EdgeDataFactory.sparse(false, Double.POSITIVE_INFINITY, 4);

    edd.set(1, 1, 0.0);
    edd.set(0, 1, 1.0);
    edd.set(1, 2, 2.0);
    edd.set(3, 2, 3.0);

    final byte[] bytes = ByteStreams.toByteArray(edd.createStream());

    final EdgeData res = new EdgeDataSparse().fromStream(new ByteArrayInputStream(bytes));

    final byte[] bytes2 = ByteStreams.toByteArray(res.createStream());

    assertTrue(Arrays.equals(bytes, bytes2));

    final EdgeData res2 = new EdgeDataSparse().fromStream(new ByteArrayInputStream(bytes2));

    assertEquals(1.0, res2.get(0, 1));
    assertEquals(Double.POSITIVE_INFINITY, res2.get(1, 0));

    assertEquals(2.0, res2.get(1, 2));
    assertEquals(Double.POSITIVE_INFINITY, res2.get(2, 1));

    assertEquals(Double.POSITIVE_INFINITY, res2.get(2, 3));
    assertEquals(3.0, res2.get(3, 2));

    assertEquals(Double.POSITIVE_INFINITY, res2.get(3, 3));
    assertEquals(Double.POSITIVE_INFINITY, res2.get(2, 2));
    assertEquals(0.0, res2.get(1, 1));
    assertEquals(Double.POSITIVE_INFINITY, res2.get(0, 0));

    assertEquals(Double.POSITIVE_INFINITY, res2.get(0, 3));
    assertEquals(Double.POSITIVE_INFINITY, res2.get(3, 0));
    assertEquals(Double.POSITIVE_INFINITY, res2.get(0, 2));
    assertEquals(Double.POSITIVE_INFINITY, res2.get(2, 0));
  }
