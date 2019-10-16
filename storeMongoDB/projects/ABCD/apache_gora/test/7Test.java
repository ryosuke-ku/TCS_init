  public void testMemStoreDeleteByQueryFields() throws Exception {

    DataStore<String, WebPage> store = new MemStore<>();
    BeanFactory<String, WebPage> beanFactory = new BeanFactoryImpl<>(String.class, WebPage.class);
    store.setBeanFactory(beanFactory);
    Query<String, WebPage> query;

    //test 5 - delete all with some fields
    WebPageDataCreator.createWebPageData(store);

    query = store.newQuery();
    query.setFields("outlinks", "parsedContent", "content");

    Query<String, WebPage> newQuery = store.newQuery();
    newQuery.setStartKey(SORTED_URLS[0]);
    newQuery.setEndKey(SORTED_URLS[9]);
    newQuery.setFields("outlinks", "parsedContent", "content");

    DataStoreTestUtil.assertNumResults(newQuery, URLS.length);
    store.deleteByQuery(query);
    store.deleteByQuery(query);
    store.deleteByQuery(query);//don't you love that HBase sometimes does not delete arbitrarily

    store.flush();

    DataStoreTestUtil.assertNumResults(store.newQuery(), URLS.length);

    //assert that data is deleted
    for (String SORTED_URL : SORTED_URLS) {
      WebPage page = store.get(SORTED_URL);
      assertNotNull(page);

      assertNotNull(page.getUrl());
      assertEquals(page.getUrl().toString(), SORTED_URL);
      assertEquals("Map of Outlinks should have a size of '0' as the deleteByQuery "
          + "not only removes the data but also the data structure.", 0, page.getOutlinks().size());
      assertEquals(0, page.getParsedContent().size());
      if (page.getContent() != null) {
        LOG.info("url:" + page.getUrl().toString());
        LOG.info("limit:" + page.getContent().limit());
      } else {
        assertNull(page.getContent());
      }
    }

    //test 6 - delete some with some fields
    WebPageDataCreator.createWebPageData(store);

    query = store.newQuery();
    query.setFields("url");
    String startKey = SORTED_URLS[NUM_KEYS];
    String endKey = SORTED_URLS[SORTED_URLS.length - NUM_KEYS];
    query.setStartKey(startKey);
    query.setEndKey(endKey);

    DataStoreTestUtil.assertNumResults(store.newQuery(), URLS.length);
    store.deleteByQuery(query);
    store.deleteByQuery(query);
    store.deleteByQuery(query);//don't you love that HBase sometimes does not delete arbitrarily

    store.flush();

    DataStoreTestUtil.assertNumResults(store.newQuery(), URLS.length);

    //assert that data is deleted
    for (int i = 0; i < URLS.length; i++) {
      WebPage page = store.get(URLS[i]);
      assertNotNull(page);
      if( URLS[i].compareTo(startKey) < 0 || URLS[i].compareTo(endKey) > 0) {
        //not deleted
        DataStoreTestUtil.assertWebPage(page, i);
      } else {
        //deleted
        assertNull(page.getUrl());
        assertNotNull(page.getOutlinks());
        assertNotNull(page.getParsedContent());
        assertNotNull(page.getContent());
        assertTrue(page.getOutlinks().size() > 0);
        assertTrue(page.getParsedContent().size() > 0);
      }
    }

  }
