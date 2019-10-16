  public void indexNameShouldBePrefixDashFormattedTimestamp() {
    long time = 987654321L;
    Event event = new SimpleEvent();
    Map<String, String> headers = new HashMap<String, String>();
    headers.put("timestamp", Long.toString(time));
    event.setHeaders(headers);
    assertEquals("prefix-" + indexNameBuilder.getFastDateFormat().format(time),
        indexNameBuilder.getIndexName(event));
  }
