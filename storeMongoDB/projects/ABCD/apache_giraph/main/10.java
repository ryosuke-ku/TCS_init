  public Iterator<MutableEdge<LongWritable, NullWritable>> mutableIterator() {
    trim();
    return new Iterator<MutableEdge<LongWritable, NullWritable>>() {
      private final Iterator<LongWritable> reader = edges.iterator();

      /** Representative edge object. */
      private final MutableEdge<LongWritable, NullWritable> representativeEdge =
          EdgeFactory.createReusable(new LongWritable());

      @Override
      public boolean hasNext() {
        return reader.hasNext();
      }

      @Override
      public MutableEdge<LongWritable, NullWritable> next() {
        representativeEdge.getTargetVertexId().set(reader.next().get());
        return representativeEdge;
      }

      @Override
      public void remove() {
        reader.remove();
      }
    };
  }
