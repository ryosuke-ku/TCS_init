  public void testReadData1Line() throws Exception {
    final TableMetaData tableMetaData = _connectorRepository.getDatabaseMetaData(CONNECTOR_ID1).getTableMetaData("FOO_USER");

    final List<Map<String, Object>> tableData = _objectUnderTest.readTableData(CONNECTOR_ID1, tableMetaData, 1);
    assertEquals(1, tableData.size());
  }
