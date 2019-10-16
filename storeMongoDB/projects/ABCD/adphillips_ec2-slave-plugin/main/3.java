  public void launch(SlaveComputer computer, TaskListener listener) throws IOException, InterruptedException {
    try {
      if (curInstanceId != null && getInstanceState(curInstanceId) == Pending) {
        throw new IllegalStateException("EC2 Instance " + curInstanceId
            + " is in Pending state.  Not sure what to do here, try again?");
      }

      if (curInstanceId == null || getInstanceState(curInstanceId) == Terminated
          || getInstanceState(curInstanceId) == ShuttingDown) {
        //only create a new EC2 instance if we haven't tried before or the instance was terminated externally
        preLaunch(listener.getLogger());
        preLaunchOk = true;
      } else {
        LOGGER.info("Skipping EC2 part of launch, since the instance is already running");
      }
    } catch (IllegalStateException ise) {
      listener.error(ise.getMessage());
      return;
    } catch (AmazonServiceException ase) {
      listener.error(ase.getMessage());
      return;
    } catch (AmazonClientException ace) {
      listener.error(ace.getMessage());
      return;
    }

    LOGGER.info("EC2 instance " + curInstanceId
        + " has been created to serve as a Jenkins slave.  Passing control to computer launcher.");
    computerLauncher = computerConnector.launch(getInstancePublicHostName(), listener);
    computerLauncher.launch(computer, listener);
  }
