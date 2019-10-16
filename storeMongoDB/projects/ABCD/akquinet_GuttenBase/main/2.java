  public void dropForeignKeys(final String targetId) throws SQLException
  {
    new ScriptExecutorTool(_connectorRepository).executeScript(targetId, true, true, createDropForeignKeyStatements(targetId));
  }
