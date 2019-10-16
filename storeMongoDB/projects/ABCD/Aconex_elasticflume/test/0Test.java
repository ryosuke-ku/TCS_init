    public void appendDifferentTypesOfLogMessage() throws IOException, InterruptedException {
        ElasticSearchSink sink = createAndOpenSink();
        Map<String, byte[]> attributes = new HashMap<String, byte[]>();
        attributes.put("attr1", new String("qux quux quuux").getBytes());
        attributes.put("attr2", new String("value2").getBytes());
        attributes.put("attr3", new String("{\"key\":\"value\"}").getBytes());

        Event event = new EventImpl("message goes here".getBytes(), 0, Priority.INFO, System.nanoTime(),
                "localhost", attributes);

        sink.append(event);
        sink.append(new EventImpl("bleh foo baz bar".getBytes(), 1, Priority.WARN, System.nanoTime(), "notlocalhost"));
        sink.append(new EventImpl("{\"key\":\"value\"}".getBytes(), 2, Priority.DEBUG, System.nanoTime(), "jsonbody"));
        sink.append(new EventImpl("{\"key\":\"value\",\"complex\":{\"subkey\":\"subvalue\"}}".getBytes(), 3, Priority.DEBUG,
                System.nanoTime(), "complexjsonbody"));

        sink.close();

        searchClient.admin().indices().refresh(refreshRequest(INDEX_NAME)).actionGet();

        assertBasicSearch(event);
        assertPrioritySearch(event);
        assertHostSearch(event);
        assertBodySearch(event);
        assertFieldsSearch(event);
        assertJsonBody(event);
        assertComplexJsonBody(event);
    }
