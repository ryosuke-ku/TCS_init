  public void testMutateEdges() {
    LongDiffNullArrayEdges edges = getEdges();

    edges.initialize();

    // Add 10 edges with id i, for i = 0..9
    for (int i = 0; i < 10; ++i) {
      edges.add(createEdge(i));
    }

    // Use the mutable iterator to remove edges with even id
    Iterator<MutableEdge<LongWritable, NullWritable>> edgeIt =
        edges.mutableIterator();
    while (edgeIt.hasNext()) {
      if (edgeIt.next().getTargetVertexId().get() % 2 == 0) {
        edgeIt.remove();
      }
    }

    // We should now have 5 edges
    assertEquals(5, edges.size());
    // The edge ids should be all odd
    for (Edge<LongWritable, NullWritable> edge : edges) {
      assertEquals(1, edge.getTargetVertexId().get() % 2);
    }
  }
