  public TableSchema getResultSetMetadata(OperationHandle opHandle)
      throws HiveSQLException {
    TableSchema tableSchema = sessionManager.getOperationManager()
        .getOperation(opHandle).getParentSession().getResultSetMetadata(opHandle);
    LOG.debug(opHandle + ": getResultSetMetadata()");
    return tableSchema;
  }
