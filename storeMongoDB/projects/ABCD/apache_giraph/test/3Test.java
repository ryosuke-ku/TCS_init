  public void testAvoidOverflowWithZero() {
    LongDiffNullArrayEdges edges = getEdges();

    List<Edge<LongWritable, NullWritable>> initialEdges = Lists.newArrayList(
        createEdge(5223372036854775807L), createEdge(-4223372036854775807L), createEdge(0));
    edges.initialize(initialEdges);
    assertEdges(edges, -4223372036854775807L, 0, 5223372036854775807L);
  }
