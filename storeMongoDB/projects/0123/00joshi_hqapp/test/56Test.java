  static void nukeTestDbs() {
    // REALLY NUKE THE OLD TESTS

    try {
      String json = new DocFetch().db("").docId("_all_dbs").to().fire().json();
      String[] strings = CouchMetaDriver.gson().fromJson(json, String[].class);
      for (String s : strings) {
        if (s.startsWith(SOMEDBPREFIX))
          new DbDelete().db(s).to().fire().tx();
      }
    } catch (JsonSyntaxException e) {
      e.printStackTrace();
      fail();
    }
  }
