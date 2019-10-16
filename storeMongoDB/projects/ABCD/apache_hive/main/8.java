  public OperationHandle executeStatement(SessionHandle sessionHandle, String statement,
      Map<String, String> confOverlay, long queryTimeout) throws HiveSQLException {
    OperationHandle opHandle =
        sessionManager.getSession(sessionHandle).executeStatement(statement, confOverlay,
            queryTimeout);
    LOG.debug(sessionHandle + ": executeStatement()");
    return opHandle;
  }
