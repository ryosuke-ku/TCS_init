  void start() throws Exception {

    if (logPathFiles == null || logPathFiles.length == 0) {
      return;
    }
    boolean isProcessFile = getBooleanValue("process_file", true);
    if (isProcessFile) {
      if (isTail()) {
        processFile(logPathFiles[0]);
      } else {
        for (File file : logPathFiles) {
          try {
            processFile(file);
            if (isClosed() || isDrain()) {
              logger.info("isClosed or isDrain. Now breaking loop.");
              break;
            }
          } catch (Throwable t) {
            logger.error("Error processing file=" + file.getAbsolutePath(), t);
          }
        }
      }
      close();
    }else{
      copyFiles(logPathFiles);
    }
    
  }
