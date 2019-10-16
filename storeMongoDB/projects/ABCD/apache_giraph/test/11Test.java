  public void testSerialization() throws IOException {
    LongDiffNullArrayEdges edges = getEdges();

    edges.initialize();

    // Add 10 edges with id i, for i = 0..9
    for (int i = 0; i < 10; ++i) {
      edges.add(createEdge(i));
    }

    edges.trim();

    // Use the mutable iterator to remove edges with even id
    Iterator<MutableEdge<LongWritable, NullWritable>> edgeIt =
        edges.mutableIterator();
    while (edgeIt.hasNext()) {
      if (edgeIt.next().getTargetVertexId().get() % 2 == 0) {
        edgeIt.remove();
      }
    }

    // We should now have 5 edges
    assertEdges(edges, 1, 3, 5, 7, 9);

    ByteArrayOutputStream arrayStream = new ByteArrayOutputStream();
    DataOutputStream tempBuffer = new DataOutputStream(arrayStream);

    edges.write(tempBuffer);

    byte[] binary = arrayStream.toByteArray();

    assertTrue("Serialized version should not be empty ", binary.length > 0);

    edges = getEdges();
    edges.readFields(new UnsafeByteArrayInputStream(binary));

    assertEquals(5, edges.size());

    for (Edge<LongWritable, NullWritable> edge : edges) {
      assertEquals(1, edge.getTargetVertexId().get() % 2);
    }
  }
