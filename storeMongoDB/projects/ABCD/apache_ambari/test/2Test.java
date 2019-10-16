  public void testOutputToSolr_uploadData() throws Exception {
    LOG.info("testOutputToSolr_uploadData()");

    Map<String, Object> config = new HashMap<String, Object>();
    config.put("url", "some url");
    config.put("workers", "3");
    config.put("collection", "some collection");

    outputSolr.loadConfig(config);
    outputSolr.init();

    Map<Integer, SolrInputDocument> expectedDocs = new HashMap<>();

    int count = 0;
    for (int i = 0; i < 10; i++) {
      Map<String, Object> jsonObj = new HashMap<>();
      for (int j = 0; j < 3; j++)
        jsonObj.put("name" + ++count, "value" + ++count);
      jsonObj.put("id", ++count);

      InputMarker inputMarker = new InputMarker();
      inputMarker.input = EasyMock.mock(Input.class);
      outputSolr.write(jsonObj, inputMarker);

      SolrInputDocument doc = new SolrInputDocument();
      for (Map.Entry<String, Object> e : jsonObj.entrySet())
        doc.addField(e.getKey(), e.getValue());

      expectedDocs.put(count, doc);
    }

    Thread.sleep(100);
    while (outputSolr.getPendingCount() > 0)
      Thread.sleep(100);

    int waitToFinish = 0;
    if (receivedDocs.size() < 10 && waitToFinish < 10) {
      Thread.sleep(100);
      waitToFinish++;
    }

    Set<Integer> ids = new HashSet<>();
    ids.addAll(receivedDocs.keySet());
    ids.addAll(expectedDocs.keySet());
    for (Integer id : ids) {
      SolrInputDocument receivedDoc = receivedDocs.get(id);
      SolrInputDocument expectedDoc = expectedDocs.get(id);

      assertNotNull("No document received for id: " + id, receivedDoc);
      assertNotNull("No document expected for id: " + id, expectedDoc);

      Set<String> fieldNames = new HashSet<>();
      fieldNames.addAll(receivedDoc.getFieldNames());
      fieldNames.addAll(expectedDoc.getFieldNames());

      for (String fieldName : fieldNames) {
        Object receivedValue = receivedDoc.getFieldValue(fieldName);
        Object expectedValue = expectedDoc.getFieldValue(fieldName);

        assertNotNull("No received document field found for id: " + id + ", fieldName: " + fieldName, receivedValue);
        assertNotNull("No expected document field found for id: " + id + ", fieldName: " + fieldName, expectedValue);

        assertEquals("Field value not matching for id: " + id + ", fieldName: " + fieldName, receivedValue,
            expectedValue);
      }
    }
  }
