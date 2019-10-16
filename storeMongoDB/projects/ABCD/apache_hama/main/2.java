  public static void readFromFile(String pathString, Writable result,
      HamaConfiguration conf) throws IOException {
    FileSystem fs = FileSystem.get(conf);
    SequenceFile.Reader reader = null;
    Path path = new Path(pathString);
    List<String> filePaths = new ArrayList<String>();
    if (!fs.isFile(path)) {
      FileStatus[] stats = fs.listStatus(path);
      for (FileStatus stat : stats) {
        filePaths.add(stat.getPath().toUri().getPath());
      }
    } else if (fs.isFile(path)) {
      filePaths.add(path.toString());
    }

    try {
      for (String filePath : filePaths) {
        reader = new SequenceFile.Reader(fs, new Path(filePath), conf);
        IntWritable key = new IntWritable();
        reader.next(key, result);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    } finally {
      if (reader != null)
        reader.close();
    }
  }
