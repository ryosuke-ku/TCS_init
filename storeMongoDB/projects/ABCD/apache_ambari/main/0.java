  public synchronized void add(String logEvent) {
    currentSpoolBufferedWriter.println(logEvent);
    currentSpoolerContext.logEventSpooled();
    if (rolloverCondition.shouldRollover(currentSpoolerContext)) {
      logger.info("Trying to rollover based on rollover condition");
      tryRollover();
    }
  }
nAlgo();

    String keySuffix = fileToUpload.getName() + "." + compressionAlgo;
    String s3Path = new S3LogPathResolver().
        getResolvedPath(s3OutputConfiguration.getS3Path()+S3Util.S3_PATH_SEPARATOR+logType,
            keySuffix, s3OutputConfiguration.getCluster());
    logger.info(String.format("keyPrefix=%s, keySuffix=%s, s3Path=%s",
        s3OutputConfiguration.getS3Path(), keySuffix, s3Path));
    File sourceFile = createCompressedFileForUpload(fileToUpload, compressionAlgo);

    logger.info("Starting S3 upload " + sourceFile + " -> " + bucketName + ", " + s3Path);
    s3UtilInstance.uploadFileTos3(bucketName, s3Path, sourceFile, s3AccessKey,
        s3SecretKey);

    // delete local compressed file
    sourceFile.delete();
    if (deleteOnEnd) {
      logger.info("Deleting input file as required");
      if (!fileToUpload.delete()) {
        logger.error("Could not delete file " + fileToUpload.getAbsolutePath() + " after upload to S3");
      }
    }
    return s3Path;
  }
