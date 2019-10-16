    public void testBadJournalCookie() throws Exception {
        ServerConfiguration conf1 = TestBKConfiguration.newServerConfiguration()
            .setJournalDirName(newDirectory())
            .setLedgerDirNames(new String[] { newDirectory() })
            .setBookiePort(bookiePort);
        Cookie.Builder cookieBuilder = Cookie.generateCookie(conf1);
        Cookie c = cookieBuilder.build();
        c.writeToZooKeeper(zkc, conf1, Version.NEW);

        String journalDir = newDirectory();
        String ledgerDir = newDirectory();
        ServerConfiguration conf2 = TestBKConfiguration.newServerConfiguration()
            .setZkServers(zkUtil.getZooKeeperConnectString())
            .setJournalDirName(journalDir)
            .setLedgerDirNames(new String[] { ledgerDir })
            .setBookiePort(bookiePort);
        Cookie.Builder cookieBuilder2 = Cookie.generateCookie(conf2);
        Cookie c2 = cookieBuilder2.build();
        c2.writeToDirectory(new File(journalDir, "current"));
        c2.writeToDirectory(new File(ledgerDir, "current"));

        try {
            Bookie b = new Bookie(conf2);
            fail("Shouldn't have been able to start");
        } catch (BookieException.InvalidCookieException ice) {
            // correct behaviour
        }
    }
