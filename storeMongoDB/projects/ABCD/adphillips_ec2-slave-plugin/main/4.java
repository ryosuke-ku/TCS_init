  public void preLaunch(PrintStream logger) throws InterruptedException {

    logger.println("Creating new EC2 instance from AMI [" + ami + "]...");
    if (testMode)
      return;

    curInstanceId = launchInstanceFromImage();

    int retries = 0;
    InstanceStateName state = null;

    while (++retries <= maxRetries) {

      logger.println(MessageFormat.format("checking state of instance [{0}]...", curInstanceId));

      state = getInstanceState(curInstanceId);

      logger.println(MessageFormat.format("state of instance [{0}] is [{1}]", curInstanceId, state.toString()));
      if (state == Running) {
        logger.println(MessageFormat.format(
            "instance [{0}] is running, proceeding to launching Jenkins on this instance", curInstanceId));
        return;
      } else if (state == Pending) {
        logger.println(MessageFormat.format("instance [{0}] is pending, waiting for [{1}] seconds before retrying",
            curInstanceId, retryIntervalSeconds));
        Thread.sleep(retryIntervalSeconds * 1000);
      } else {
        String msg = MessageFormat.format("instance [{0}] encountered unexpected state [{1}]. Aborting launch",
            curInstanceId, state.toString());
        logger.println(msg);
        throw new IllegalStateException(msg);
      }
    }
    throw new IllegalStateException("Maximum Number of retries " + maxRetries + " exceeded. Aborting launch");
  }
