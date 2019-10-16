  public void add(Edge<LongWritable, NullWritable> edge) {
    edges.add(edge.getTargetVertexId().get());
  }
