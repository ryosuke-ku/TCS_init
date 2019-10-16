  public void rollover() {
    logger.info("Rollover condition detected, rolling over file: " + currentSpoolFile);
    currentSpoolBufferedWriter.flush();
    if (currentSpoolFile.length()==0) {
      logger.info("No data in file " + currentSpoolFile + ", not doing rollover");
    } else {
      currentSpoolBufferedWriter.close();
      rolloverHandler.handleRollover(currentSpoolFile);
      logger.info("Invoked rollover handler with file: " + currentSpoolFile);
      initializeSpoolState();
    }
    boolean status = rolloverInProgress.compareAndSet(true, false);
    if (!status) {
      logger.error("Should have reset rollover flag!!");
    }
  }
