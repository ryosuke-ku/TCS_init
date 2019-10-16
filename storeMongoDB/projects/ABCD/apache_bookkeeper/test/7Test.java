    public void testRestartWithHostNameAsBookieID() throws Exception {
        String[] ledgerDirs = new String[] { newDirectory(), newDirectory(),
                newDirectory() };
        String journalDir = newDirectory();
        ServerConfiguration conf = TestBKConfiguration.newServerConfiguration()
                .setZkServers(zkUtil.getZooKeeperConnectString())
                .setJournalDirName(journalDir).setLedgerDirNames(ledgerDirs)
                .setBookiePort(bookiePort);
        Bookie b = new Bookie(conf); // should work fine
        b.start();
        b.shutdown();

        conf.setUseHostNameAsBookieID(true);
        b = new Bookie(conf);
        b.start();
        assertTrue("Fails to recognize bookie which was started with IPAddr as ID", !conf.getUseHostNameAsBookieID());
        b.shutdown();
    }
