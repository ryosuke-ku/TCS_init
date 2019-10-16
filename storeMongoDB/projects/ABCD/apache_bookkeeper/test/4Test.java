    public void testLedgerLayout() throws Exception {
        ClientConfiguration conf = new ClientConfiguration();
        conf.setLedgerManagerFactoryClass(HierarchicalLedgerManagerFactory.class);
        String ledgerRootPath = "/testLedgerLayout";

        zkc.create(ledgerRootPath, new byte[0],
                   Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        LedgerLayout layout = LedgerLayout.readLayout(zkc, ledgerRootPath);
        assertTrue("Layout should be null", layout == null);

        String testName = "foobar";
        int testVersion = 0xdeadbeef;
        // use layout defined in configuration also create it in zookeeper
        LedgerLayout layout2 = new LedgerLayout(testName, testVersion);
        layout2.store(zkc, ledgerRootPath);

        layout = LedgerLayout.readLayout(zkc, ledgerRootPath);
        assertEquals(testName, layout.getManagerFactoryClass());
        assertEquals(testVersion, layout.getManagerVersion());
    }
