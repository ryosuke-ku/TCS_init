  public static void main(String[] args) throws IOException,
      InterruptedException, ClassNotFoundException {
    if (args.length < 3)
      printUsage();

    // Graph job configuration
    HamaConfiguration conf = new HamaConfiguration();
    GraphJob ssspJob = new GraphJob(conf, SSSP.class);
    // Set the job name
    ssspJob.setJobName("Single Source Shortest Path");

    conf.set(START_VERTEX, args[0]);
    ssspJob.setInputPath(new Path(args[1]));
    ssspJob.setOutputPath(new Path(args[2]));

    if (args.length == 4) {
      ssspJob.setNumBspTask(Integer.parseInt(args[3]));
    }

    ssspJob.setVertexClass(ShortestPathVertex.class);
    ssspJob.setCombinerClass(MinIntCombiner.class);
    ssspJob.setInputFormat(TextInputFormat.class);
    ssspJob.setInputKeyClass(LongWritable.class);
    ssspJob.setInputValueClass(Text.class);

    ssspJob.setPartitioner(HashPartitioner.class);
    ssspJob.setOutputFormat(TextOutputFormat.class);
    ssspJob.setVertexInputReaderClass(SSSPTextReader.class);
    ssspJob.setOutputKeyClass(Text.class);
    ssspJob.setOutputValueClass(IntWritable.class);
    // Iterate until all the nodes have been reached.
    ssspJob.setMaxIteration(Integer.MAX_VALUE);

    ssspJob.setVertexIDClass(Text.class);
    ssspJob.setVertexValueClass(IntWritable.class);
    ssspJob.setEdgeValueClass(IntWritable.class);

    long startTime = System.currentTimeMillis();
    if (ssspJob.waitForCompletion(true)) {
      System.out.println("Job Finished in "
          + (System.currentTimeMillis() - startTime) / 1000.0 + " seconds");
    }
  }
