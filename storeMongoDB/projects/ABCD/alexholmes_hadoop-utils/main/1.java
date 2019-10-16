    public boolean runJob(final JobConf jobConf, final Integer numMapTasks,
                          final Integer numReduceTasks, final InputSampler.Sampler<K, V> sampler,
                          final Class<? extends CompressionCodec> codecClass,
                          final Class<? extends CompressionCodec> mapCodecClass,
                          final boolean createLzopIndexes,
                          final String inputDirAsString, final String outputDirAsString)
            throws IOException, URISyntaxException {

        jobConf.setJarByClass(Sort.class);
        jobConf.setJobName("sorter");

        JobClient client = new JobClient(jobConf);
        ClusterStatus cluster = client.getClusterStatus();

        if (numMapTasks != null) {
            jobConf.setNumMapTasks(numMapTasks);
        }
        if (numReduceTasks != null) {
            jobConf.setNumReduceTasks(numReduceTasks);
        } else {
            int numReduces = (int) (cluster.getMaxReduceTasks() * 0.9);
            String sortReduces = jobConf.get("test.sort.reduces_per_host");
            if (sortReduces != null) {
                numReduces = cluster.getTaskTrackers() * Integer.parseInt(sortReduces);
            }

            // Set user-supplied (possibly default) job configs
            jobConf.setNumReduceTasks(numReduces);
        }

        jobConf.setMapperClass(IdentityMapper.class);
        jobConf.setReducerClass(SortReduce.class);

        jobConf.setInputFormat(SortInputFormat.class);

        jobConf.setMapOutputKeyClass(Text.class);
        jobConf.setMapOutputValueClass(Text.class);
        jobConf.setOutputKeyClass(Text.class);
        jobConf.setOutputValueClass(Text.class);

        if (mapCodecClass != null) {
            jobConf.setMapOutputCompressorClass(mapCodecClass);
        }

        if (codecClass != null) {
            jobConf.setBoolean("mapred.output.compress", true);
            jobConf.setClass("mapred.output.compression.codec",
                    codecClass, CompressionCodec.class);
        }

        FileInputFormat.setInputPaths(jobConf, inputDirAsString);
        FileOutputFormat.setOutputPath(jobConf, new Path(outputDirAsString));

        if (sampler != null) {
            System.out.println("Sampling input to effect total-order sort...");
            jobConf.setPartitionerClass(TotalOrderPartitioner.class);
            Path inputDir = FileInputFormat.getInputPaths(jobConf)[0];

            FileSystem fileSystem = FileSystem.get(jobConf);

            if (fileSystem.exists(inputDir) && fileSystem.isFile(inputDir)) {
                inputDir = inputDir.getParent();
            }
            inputDir = inputDir.makeQualified(inputDir.getFileSystem(jobConf));
            Path partitionFile = new Path(inputDir, "_sortPartitioning");
            TotalOrderPartitioner.setPartitionFile(jobConf, partitionFile);
            InputSampler.writePartitionFile(jobConf, sampler);
            URI partitionUri = new URI(partitionFile.toString()
                    + "#" + "_sortPartitioning");
            DistributedCache.addCacheFile(partitionUri, jobConf);
            DistributedCache.createSymlink(jobConf);
        }

        System.out.println("Running on "
                + cluster.getTaskTrackers()
                + " nodes to sort from "
                + FileInputFormat.getInputPaths(jobConf)[0] + " into "
                + FileOutputFormat.getOutputPath(jobConf)
                + " with " + jobConf.getNumReduceTasks() + " reduces.");
        Date startTime = new Date();
        System.out.println("Job started: " + startTime);
        jobResult = JobClient.runJob(jobConf);
        Date endTime = new Date();
        System.out.println("Job ended: " + endTime);
        System.out.println("The job took "
                + TimeUnit.MILLISECONDS.toSeconds(endTime.getTime() - startTime.getTime())
                + " seconds.");

        if (jobResult.isSuccessful()) {
            if (createLzopIndexes && codecClass != null && LzopCodec.class.equals(codecClass)) {
                new LzoIndexer(jobConf).index(new Path(outputDirAsString));
            }
            return true;
        }
        return false;
    }
