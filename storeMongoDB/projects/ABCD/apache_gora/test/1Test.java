  public static void setUpClass() throws Exception {
    List<String> cacheNames = new ArrayList<>();
    cacheNames.add(Employee.class.getSimpleName());
    cacheNames.add(WebPage.class.getSimpleName());
    setTestDriver(new GoraInfinispanTestDriver(1, cacheNames));
    DataStoreTestBase.setUpClass();
  }
