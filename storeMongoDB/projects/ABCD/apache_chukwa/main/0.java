  public static Class loadDriver() throws ClassNotFoundException {
    String jdbcDriver = System.getenv("JDBC_DRIVER");
    if(jdbcDriver == null) {
      jdbcDriver = "com.mysql.jdbc.Driver";
    }
    return Class.forName(jdbcDriver);
  }
