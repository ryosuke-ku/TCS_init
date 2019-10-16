  public void testPutGet() throws Exception {
    String key = "org.apache.gora:http:/";
    DataStore<String, WebPage> store = new MemStore<>();
    assumeTrue(store.get(key, new String[0]) == null);
    store.put(key, WebPage.newBuilder().build());
    assertNotNull(store.get(key, new String[0]));
    store.close();
  }
