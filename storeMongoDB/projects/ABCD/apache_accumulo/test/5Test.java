  public void testFetchColumn() {
    try (ScannerOptions options = new ScannerOptions()) {
      assertEquals(0, options.getFetchedColumns().size());
      IteratorSetting.Column col = new IteratorSetting.Column(new Text("family"), new Text("qualifier"));
      options.fetchColumn(col);
      SortedSet<Column> fetchedColumns = options.getFetchedColumns();
      assertEquals(1, fetchedColumns.size());
      Column fetchCol = fetchedColumns.iterator().next();
      assertEquals(col.getColumnFamily(), new Text(fetchCol.getColumnFamily()));
      assertEquals(col.getColumnQualifier(), new Text(fetchCol.getColumnQualifier()));
    }
  }
