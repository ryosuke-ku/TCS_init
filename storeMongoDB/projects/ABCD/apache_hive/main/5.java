  public void closeSession(SessionHandle sessionHandle)
      throws HiveSQLException {
    sessionManager.closeSession(sessionHandle);
    LOG.debug(sessionHandle + ": closeSession()");
  }
