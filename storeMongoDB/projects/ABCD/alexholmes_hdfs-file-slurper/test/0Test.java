  public void testCopyFromLocalToDfs() throws Exception {
    Configuration conf = new Configuration();
    final FileSystem srcFs = FileSystem.getLocal(conf);
    final FileSystem destFs = FileSystem.getLocal(conf);
    final String namenode = destFs.getUri().toString();
    System.out.println("NN = " + namenode);
    if (namenode.startsWith("hdfs://")) {

      Path localBaseDir = srcFs.makeQualified(new Path(TEST_ROOT_DIR, "test-slurper"));

      System.out.println(localBaseDir.toString());


      Path localInDir = new Path(localBaseDir, "in");
      Path localWorkDir = new Path(localBaseDir, "work");
      Path localErrorDir = new Path(localBaseDir, "error");
      Path localCompleteDir = new Path(localBaseDir, "completed");

      srcFs.delete(localBaseDir, true);

      srcFs.mkdirs(localInDir);

      Path localInFile = new Path(localInDir, "test-file");
      TestFile inFile = new TestFile(srcFs, localInFile);


      Path hdfsBaseDir = destFs.makeQualified(new Path("/tmp", "test-slurper"));
      Path hdfsDestDir = new Path(hdfsBaseDir, "out");
      Path hdfsStageDir = new Path(hdfsBaseDir, "stage");

      System.out.println(hdfsBaseDir.toString());

      Config c = new Config();
      c.setSrcDir(localInDir)
          .setWorkDir(localWorkDir)
          .setErrorDir(localErrorDir)
          .setCompleteDir(localCompleteDir)
          .setDestDir(hdfsDestDir)
          .setDestStagingDir(hdfsStageDir)
          .setPollSleepPeriodMillis(1000)
          .setSrcFs(srcFs)
          .setDestFs(destFs);


      FileSystemManager fsm = new FileSystemManager(c);

      WorkerThread wt = new WorkerThread(c, fsm, TimeUnit.MILLISECONDS, 1);

      wt.doWork();

      Path expectedDestinationFile = new Path(hdfsDestDir, localInFile.getName());

      assertTrue("File doesn't exist '" + expectedDestinationFile.toUri() + "'", destFs.exists(expectedDestinationFile));

      assertEquals(inFile.getCRC32(), hdfsFileCRC32(destFs, null, expectedDestinationFile));
    }
  }
