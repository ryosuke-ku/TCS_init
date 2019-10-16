  public void testBug3() throws Exception {
    KeyExtent mte1 = new KeyExtent(MetadataTable.ID, new Text("1;c"), RTE.getEndRow());
    KeyExtent mte2 = new KeyExtent(MetadataTable.ID, new Text("1;f"), new Text("1;c"));
    KeyExtent mte3 = new KeyExtent(MetadataTable.ID, new Text("1;j"), new Text("1;f"));
    KeyExtent mte4 = new KeyExtent(MetadataTable.ID, new Text("1;r"), new Text("1;j"));
    KeyExtent mte5 = new KeyExtent(MetadataTable.ID, null, new Text("1;r"));

    KeyExtent ke1 = new KeyExtent("1", null, null);

    TServers tservers = new TServers();
    TestTabletLocationObtainer ttlo = new TestTabletLocationObtainer(tservers);

    RootTabletLocator rtl = new TestRootTabletLocator();

    TabletLocatorImpl rootTabletCache = new TabletLocatorImpl(MetadataTable.ID, rtl, ttlo, new YesLockChecker());
    TabletLocatorImpl tab0TabletCache = new TabletLocatorImpl("1", rootTabletCache, ttlo, new YesLockChecker());

    setLocation(tservers, "tserver1", RTE, mte1, "tserver2");
    setLocation(tservers, "tserver1", RTE, mte2, "tserver3");
    setLocation(tservers, "tserver1", RTE, mte3, "tserver4");
    setLocation(tservers, "tserver1", RTE, mte4, "tserver5");
    setLocation(tservers, "tserver1", RTE, mte5, "tserver6");

    createEmptyTablet(tservers, "tserver2", mte1);
    createEmptyTablet(tservers, "tserver3", mte2);
    createEmptyTablet(tservers, "tserver4", mte3);
    createEmptyTablet(tservers, "tserver5", mte4);
    setLocation(tservers, "tserver6", mte5, ke1, "tserver7");

    locateTabletTest(tab0TabletCache, "a", ke1, "tserver7");

  }
