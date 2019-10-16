  public void testGetMissingValue() {
    DataStore<String, WebPage> store = new MemStore<>();
    WebPage nullWebPage = store.get("missing", new String[0]);
    assertNull(nullWebPage);
    store.close();
  }
