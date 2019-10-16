  protected static void stopHiveServer2() throws Exception {
    if (hiveServer2 != null) {
      hiveServer2.stop();
    }
  }
