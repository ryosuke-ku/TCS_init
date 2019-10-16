  public void testGetConnectionInfo() {
    {
      String url = "jdbc:mysql://localhost:3306/demo";
      ConnectionInfo ci = new ConnectionInfo(url);
      assertEquals("jdbc:mysql://localhost:3306/demo", ci.getUri());
      assertEquals(0, ci.getProperties().size());
    }
    
    {
      String url = "jdbc:mysql://localhost:3306/demo?user=example";
      ConnectionInfo ci = new ConnectionInfo(url);
      assertEquals("jdbc:mysql://localhost:3306/demo", ci.getUri());
      assertEquals(1, ci.getProperties().size());
      assertEquals("example", ci.getProperties().get("user"));
    }
    
    {
      String url = "jdbc:mysql://localhost:3306/demo?user=example&";
      ConnectionInfo ci = new ConnectionInfo(url);
      assertEquals("jdbc:mysql://localhost:3306/demo", ci.getUri());
      assertEquals(1, ci.getProperties().size());
      assertEquals("example", ci.getProperties().get("user"));
    }
    
    {
      String url = "jdbc:mysql://localhost:3306/demo?user=example&pwd";
      ConnectionInfo ci = new ConnectionInfo(url);
      assertEquals("jdbc:mysql://localhost:3306/demo", ci.getUri());
      assertEquals(2, ci.getProperties().size());
      assertEquals("example", ci.getProperties().get("user"));
      assertEquals("", ci.getProperties().get("pwd"));
    }
    
    {
      String url = "jdbc:mysql://localhost:3306/demo?user=example&pwd=";
      ConnectionInfo ci = new ConnectionInfo(url);
      assertEquals("jdbc:mysql://localhost:3306/demo", ci.getUri());
      assertEquals(2, ci.getProperties().size());
      assertEquals("example", ci.getProperties().get("user"));
      assertEquals("", ci.getProperties().get("pwd"));
    }
    
    {
      String url = "jdbc:mysql://localhost:3306/demo?user=example&pwd=ppppp";
      ConnectionInfo ci = new ConnectionInfo(url);
      assertEquals("jdbc:mysql://localhost:3306/demo", ci.getUri());
      assertEquals(2, ci.getProperties().size());
      assertEquals("example", ci.getProperties().get("user"));
      assertEquals("ppppp", ci.getProperties().get("pwd"));
    }
  }
