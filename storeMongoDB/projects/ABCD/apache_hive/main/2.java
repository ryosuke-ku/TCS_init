  public synchronized void stop() {
    if (isStarted && !isEmbedded) {
      if(server != null) {
        server.stop();
        LOG.info("Thrift server has stopped");
      }
      if((httpServer != null) && httpServer.isStarted()) {
        try {
          httpServer.stop();
          LOG.info("Http server has stopped");
        } catch (Exception e) {
          LOG.error("Error stopping Http server: ", e);
        }
      }
      isStarted = false;
    }
    super.stop();
  }
