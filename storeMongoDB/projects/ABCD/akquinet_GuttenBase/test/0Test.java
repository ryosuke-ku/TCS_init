  public void testDelimiter() throws Exception
  {
    final SQLLexer objectUnderTest = new SQLLexer(Arrays.asList("CREATE TABLE FOO(", "USER_ID bigint", ")@", "CREATE TABLE BAR(",
        "USER_ID bigint", ")@"), '@');

    final List<String> list = objectUnderTest.parse();
    assertEquals(2, list.size());
    assertEquals("CREATE TABLE FOO( USER_ID bigint )", list.get(0));
  }

    Assert.assertTrue(compatibilityIssues.isSevere());
    Assert.assertNotNull(compatibilityIssues.contains(SchemaCompatibilityIssueType.MISSING_COLUMN));
    Assert.assertNotNull(compatibilityIssues.contains(SchemaCompatibilityIssueType.INCOMPATIBLE_COLUMNS));
    final SchemaCompatibilityIssue missingForeignKeyIssue = compatibilityIssues.contains(SchemaCompatibilityIssueType.MISSING_FOREIGN_KEY);
    Assert.assertNotNull(missingForeignKeyIssue);

    final ForeignKeyMetaData foreignKeyMetaData = ((MissingForeignKeyIssue) missingForeignKeyIssue).getForeignKeyMetaData();
    Assert.assertEquals("ASSIGNED_COMPANY_ID", foreignKeyMetaData.getReferencingColumn().getColumnName());

    final SchemaCompatibilityIssue missingIndexIssue = compatibilityIssues.contains(SchemaCompatibilityIssueType.MISSING_INDEX);
    Assert.assertNotNull(missingIndexIssue);
    Assert.assertEquals("COMPANY_NAME_IDX", ((MissingIndexIssue) missingIndexIssue).getIndexMetaData().getIndexName());
  }
