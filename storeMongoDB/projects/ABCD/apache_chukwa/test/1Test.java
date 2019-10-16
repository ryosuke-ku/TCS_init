  public void testGetConnection() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
    if(false) {
      DriverManagerUtil.loadDriver().newInstance();
      String url = "jdbc:mysql://localhost:3306/test?user=root&pwd=";
      Connection conn = DriverManagerUtil.getConnection(url);
    }
  } 
