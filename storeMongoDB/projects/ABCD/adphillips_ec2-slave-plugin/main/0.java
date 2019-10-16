  public void terminateInstance(PrintStream logger) {
    logger.println("EC2InstanceComputerLauncher: Terminating EC2 instance [" + curInstanceId + "] ...");
    if (testMode)
      return;

    ec2.terminateInstances(new TerminateInstancesRequest().withInstanceIds(curInstanceId));
  }
