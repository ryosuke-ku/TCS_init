  public void testDropForeignKeys() throws Exception
  {
    assertTrue(getAllForeignKeys().size() > 0);

    _objectUnderTest.dropForeignKeys(CONNECTOR_ID);

    assertEquals(0, getAllForeignKeys().size());
  }
