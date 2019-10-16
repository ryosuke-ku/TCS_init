    public void testAbsentLedgerManagerLayout() throws Exception {
        ClientConfiguration conf = new ClientConfiguration();
        String ledgersLayout = conf.getZkLedgersRootPath() + "/"
                + BookKeeperConstants.LAYOUT_ZNODE;
        // write bad format ledger layout
        StringBuilder sb = new StringBuilder();
        sb.append(LedgerLayout.LAYOUT_FORMAT_VERSION).append("\n");
        zkc.create(ledgersLayout, sb.toString().getBytes(),
                                 Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        try {
            LedgerLayout.readLayout(zkc, conf.getZkLedgersRootPath());
            fail("Shouldn't reach here!");
        } catch (IOException ie) {
            assertTrue("Invalid exception", ie.getMessage().contains("version absent from"));
        }
    }
