  public void testSuperstepZero() throws Exception {
    // this guy should end up with an array value of 4
    Vertex<IntWritable, IntArrayListWritable, NullWritable> vertex =
        new DefaultVertex<IntWritable, IntArrayListWritable, NullWritable>();

    IntArrayListWritable alw = new IntArrayListWritable();

    SimpleTriangleClosingComputation computation =
        new SimpleTriangleClosingComputation();
    MockUtils.MockedEnvironment env = MockUtils.prepareVertexAndComputation(
        vertex, new IntWritable(1), alw, false, computation, 0L);

    vertex.addEdge(EdgeFactory.create(new IntWritable(5)));
    vertex.addEdge(EdgeFactory.create(new IntWritable(7)));

    computation.compute(vertex, Lists.<IntWritable>newArrayList(
      new IntWritable(83), new IntWritable(42)));

    env.verifyMessageSentToAllEdges(vertex, new IntWritable(5));
    env.verifyMessageSentToAllEdges(vertex, new IntWritable(7));
  }
