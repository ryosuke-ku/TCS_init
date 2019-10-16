  public void setUp() throws Exception {
    Context context = new Context();
    context.put(ElasticSearchSinkConstants.INDEX_NAME, "prefix");
    indexNameBuilder = new TimeBasedIndexNameBuilder();
    indexNameBuilder.configure(context);
  }
