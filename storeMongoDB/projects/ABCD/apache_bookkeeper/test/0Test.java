    public void testLedgerCreationShouldFailWithReadonlyBookie() throws Exception {
        killBookie(1);
        baseConf.setReadOnlyModeEnabled(true);
        startNewBookie();
        bs.get(1).getBookie().doTransitionToReadOnlyMode();
        try {
            bkc.readBookiesBlocking();
            bkc.createLedger(2, 2, DigestType.CRC32, "".getBytes());
            fail("Must throw exception, as there is one readonly bookie");
        } catch (BKException e) {
            // Expected
        }
    }
