  public void close() {
    httpclient.getConnectionManager().shutdown();
  }
