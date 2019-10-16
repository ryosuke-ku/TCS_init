      public MutableEdge<LongWritable, NullWritable> next() {
        representativeEdge.getTargetVertexId().set(reader.next().get());
        return representativeEdge;
      }
