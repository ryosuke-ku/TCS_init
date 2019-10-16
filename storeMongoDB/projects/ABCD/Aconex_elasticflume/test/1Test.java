    public void validateErrorCount() throws IOException, InterruptedException {
        ElasticSearchSink sink = createAndOpenSink();

        EventImpl invalidJsonEvent1 = new EventImpl("{ \"not json\" : no".getBytes(), 1, Priority.DEBUG, System.nanoTime(),
                "notlocalhost");
        sink.append(invalidJsonEvent1);
        sink.close();

        ReportEvent event = sink.getMetrics();
        long noOfFailedEvents = event.getLongMetric("NO_OF_FAILED_EVENTS");
        assertEquals("1 event should ", noOfFailedEvents, 1L);
    }
