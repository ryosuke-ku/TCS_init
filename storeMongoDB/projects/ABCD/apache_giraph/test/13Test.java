  public void testParallelEdges() {
    LongDiffNullArrayEdges edges = getEdges();

    List<Edge<LongWritable, NullWritable>> initialEdges = Lists.newArrayList(
        createEdge(2), createEdge(2), createEdge(2));

    edges.initialize(initialEdges);
    assertEquals(3, edges.size());

    edges.remove(new LongWritable(2));
    assertEquals(0, edges.size());

    edges.add(EdgeFactory.create(new LongWritable(2)));
    assertEquals(1, edges.size());

    edges.trim();
    assertEquals(1, edges.size());
  }
