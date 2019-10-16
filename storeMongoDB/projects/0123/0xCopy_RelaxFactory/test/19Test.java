  public void testLowLevelUpdateDoc() {
    CouchTx tx = new DocPersist().db(SOMEDB).validjson("{}").to().fire().tx();

    String data = new DocFetch().db(SOMEDB).docId(tx.id()).to().fire().json();
    Map<String, Object> obj =
        CouchMetaDriver.gson().<Map<String, Object>> fromJson(data, Map.class);
    obj.put("abc", "123");
    data = CouchMetaDriver.gson().toJson(obj);
    KouchTx updateTx = new DocPersist().db(SOMEDB).validjson(data).to().fire().tx();
    assertNotNull(updateTx);
  }
