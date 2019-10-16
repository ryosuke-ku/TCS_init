    public void setup(
        BSPPeer<IntWritable, SparseVectorWritable, IntWritable, DoubleWritable, NullWritable> peer)
        throws IOException, SyncException, InterruptedException {
      // reading input vector, which represented as matrix row
      HamaConfiguration conf = (HamaConfiguration) peer.getConfiguration();
      v = new DenseVectorWritable();
      readFromFile(conf.get(inputVectorPathString), v, conf);
      peer.sync();
    }
