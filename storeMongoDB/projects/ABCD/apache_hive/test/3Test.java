  public void testExecuteStatement() throws Exception {
    HashMap<String, String> confOverlay = new HashMap<String, String>();
    SessionHandle sessionHandle = client.openSession(
        "tom", "password", new HashMap<String, String>());
    assertNotNull(sessionHandle);

    OperationHandle opHandle;

    String queryString = "SET " + HiveConf.ConfVars.HIVE_SUPPORT_CONCURRENCY.varname
        + " = false";
    opHandle = client.executeStatement(sessionHandle, queryString, confOverlay);
    client.closeOperation(opHandle);

    queryString = "DROP TABLE IF EXISTS TEST_EXEC";
    opHandle = client.executeStatement(sessionHandle, queryString, confOverlay);
    client.closeOperation(opHandle);

    // Create a test table
    queryString = "CREATE TABLE TEST_EXEC(ID STRING)";
    opHandle = client.executeStatement(sessionHandle, queryString, confOverlay);
    client.closeOperation(opHandle);

    // Blocking execute
    queryString = "SELECT ID+1 FROM TEST_EXEC";
    opHandle = client.executeStatement(sessionHandle, queryString, confOverlay);

    OperationStatus opStatus = client.getOperationStatus(opHandle);
    checkOperationTimes(opHandle, opStatus);
    // Expect query to be completed now
    assertEquals("Query should be finished",
        OperationState.FINISHED, client.getOperationStatus(opHandle).getState());
    client.closeOperation(opHandle);

    // Cleanup
    queryString = "DROP TABLE IF EXISTS TEST_EXEC";
    opHandle = client.executeStatement(sessionHandle, queryString, confOverlay);
    client.closeOperation(opHandle);
    client.closeSession(sessionHandle);
  }
