  public void testManualViewFetch() {
    String doc =
        "{" + "  \"_id\" : \"" + DESIGN_SAMPLE + "\"," + "  \"views\" : {" + "    \"foo\" : {"
            + "      \"map\" : \"function(doc){ emit(doc.name, doc); }\"" + "    }" + "  }" + "}";
    // TODO inconsistent with DesignDocFetch
    CouchTx tx = new JsonSend().opaque(SOMEDB).validjson(doc).to().fire().tx();
    assertNotNull(tx);
    assertTrue(tx.ok());
    assertEquals(tx.id(), DESIGN_SAMPLE);

    new DocPersist().db(SOMEDB).validjson("{\"name\":\"a\",\"brand\":\"c\"}").to().fire().tx();
    new DocPersist().db(SOMEDB).validjson("{\"name\":\"b\",\"brand\":\"d\"}").to().fire().tx();
    String space =
        "hal kjfljdskjahkjsdfkajhdf halkjsdf kgasdkjfh hwroeuvbdfhjvb nv ihdfousbkvjlsdfkvbdkjfvpghblkjfgbldkgf,xjbxdl kfjbhxv,vdlkgfhbfkljdflkjh dfjgh bsjdhfg hlhgdvkjhgksdfglhs";
    new DocPersist().db(SOMEDB).validjson(
        "{\"name\":\"" + System.nanoTime() + "\",\"brand\":\"d\",\"crap\":\"" + space + "\"}").to()
        .fire().tx();
    new DocPersist().db(SOMEDB).validjson(
        "{\"name\":\"" + System.nanoTime() + "\",\"brand\":\"d\",\"crap\":\"" + space + "\"}").to()
        .fire().tx();
    new DocPersist().db(SOMEDB).validjson(
        "{\"name\":\"" + System.nanoTime() + "\",\"brand\":\"d\",\"crap\":\"" + space + "\"}").to()
        .fire().tx();
    new DocPersist().db(SOMEDB).validjson(
        "{\"name\":\"" + System.nanoTime() + "\",\"brand\":\"d\",\"crap\":\"" + space + "\"}").to()
        .fire().tx();
    new DocPersist().db(SOMEDB).validjson(
        "{\"name\":\"" + System.nanoTime() + "\",\"brand\":\"d\",\"crap\":\"" + space + "\"}").to()
        .fire().tx();
    new DocPersist().db(SOMEDB).validjson(
        "{\"name\":\"" + System.nanoTime() + "\",\"brand\":\"d\",\"crap\":\"" + space + "\"}").to()
        .fire().tx();
    new DocPersist().db(SOMEDB).validjson(
        "{\"name\":\"" + System.nanoTime() + "\",\"brand\":\"d\",\"crap\":\"" + space + "\"}").to()
        .fire().tx();
    // running view
    final ViewFetchTerminalBuilder fire =
        new ViewFetch().db(SOMEDB).type(Map.class).view(DESIGN_SAMPLE + "/_view/foo?key=\"a\"")
            .to().fire();
    CouchResultSet<?, Map<String, String>> data = fire.rows();
    assertNotNull(data);
    assertEquals(1, data.rows.size());
    assertEquals("a", data.rows.get(0).value.get("name")); // TODO no consistent way to write designdoc
    String designDoc =
        new DesignDocFetch().db(SOMEDB).designDocId(DESIGN_SAMPLE).to().fire().json();
    assertNotNull(designDoc);
    Map<String, Object> obj =
        CouchMetaDriver.gson().<Map<String, Object>> fromJson(designDoc, Map.class);

    Map<String, String> foo = (Map<String, String>) ((Map<String, ?>) obj.get("views")).get("foo");
    foo.put("map", "function(doc){ emit(doc.brand, doc); }");

    designDoc = CouchMetaDriver.gson().toJson(obj);
    tx = new JsonSend().opaque(SOMEDB).validjson(designDoc).to().fire().tx();

    assertNotNull(tx);
    assertTrue(tx.ok());
    assertFalse(obj.get("_rev").equals(tx.getRev()));
    assertEquals(obj.get("_id"), tx.id());
    try {
      TimeUnit.MILLISECONDS.sleep(500);
    } catch (InterruptedException e) {
      fail(e.toString());
    }
    data =
        new ViewFetch().db(SOMEDB).type(Map.class).view(DESIGN_SAMPLE + "/_view/foo?key=\"d\"")
            .to().fire().rows();
    assertNotNull(data);
    assertEquals(8, data.rows.size());
    assertEquals("b", data.rows.get(0).value.get("name"));

    String rev = null;
    try {
      rev = new RevisionFetch().db(SOMEDB).docId(DESIGN_SAMPLE).to().fire().json();
      tx = new DocDelete().db(SOMEDB).docId(DESIGN_SAMPLE).rev(rev).to().fire().tx();
      assert tx.ok();
    } catch (Exception e) {
      e.printStackTrace();
      fail(rev);
    }
    designDoc = new DesignDocFetch().db(SOMEDB).designDocId(DESIGN_SAMPLE).to().fire().json();
    assertNull(designDoc);
  }
