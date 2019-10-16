  public void testLowLevelFetch() {
    CouchTx tx = new DocPersist().db(SOMEDB).validjson("{\"created\":true}").to().fire().tx();

    String data = new DocFetch().db(SOMEDB).docId(tx.id()).to().fire().json();
    assertTrue(data.contains("created"));
  }
