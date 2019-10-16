  public void testShortestPaths() throws IOException, InterruptedException,
      ClassNotFoundException, InstantiationException, IllegalAccessException {

    SSSP.main(new String[] { "0", INPUT, OUTPUT, "3" });
    verifyResult();
  }
