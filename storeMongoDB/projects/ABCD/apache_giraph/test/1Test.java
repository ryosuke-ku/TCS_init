  public void testSuperstepOne() throws Exception {
    // see if the vertex interprets its incoming
    // messages properly to verify the algorithm
    Vertex<IntWritable, IntArrayListWritable, NullWritable> vertex =
        new DefaultVertex<IntWritable, IntArrayListWritable, NullWritable>();
    SimpleTriangleClosingComputation computation =
        new SimpleTriangleClosingComputation();
    MockUtils.MockedEnvironment env = MockUtils.prepareVertexAndComputation(
        vertex, new IntWritable(1), null, false, computation, 1L);

      // superstep 1: can the vertex process these correctly?
      computation.compute(vertex, Lists.<IntWritable>newArrayList(
        new IntWritable(7),
        new IntWritable(3),
        new IntWritable(4),
        new IntWritable(7),
        new IntWritable(4),
        new IntWritable(2),
        new IntWritable(4)));
      final String pairCheck = "[4, 7]";
      assertEquals(pairCheck, vertex.getValue().toString());
  }
