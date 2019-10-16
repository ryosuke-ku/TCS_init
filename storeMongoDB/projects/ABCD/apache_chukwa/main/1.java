  public static Connection getConnection(String url) throws SQLException {
    ConnectionInfo info = new ConnectionInfo(url);
    return DriverManager.getConnection(info.getUri(), info.getProperties());
  }
