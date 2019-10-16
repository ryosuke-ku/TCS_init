  public void testCreateFilter_mapField_notEquals() throws Exception {
    MapFieldValueFilter<String, WebPage> filter = createHeadersFilter();
    filter.setFilterOp(FilterOp.NOT_EQUALS);
    filter.setFilterIfMissing(true);

    DBObject dbObject = filterFactory.createFilter(filter, store);
