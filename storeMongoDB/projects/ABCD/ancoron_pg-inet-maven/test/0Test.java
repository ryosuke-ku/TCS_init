    private void assertSplitInvalid(String network, int count, String exceptionContains) {
        IPNetwork net = new IPNetwork(network);
        
        try {
            IPNetwork[] splitted = NetworkUtils.splitByCount(net, count);
            
            Assert.fail("Unexpectedly splitted the network: " + Arrays.deepToString(splitted));
        } catch(Exception x) {
            Assert.assertTrue("Unexpected exception message '" + x.getMessage()
                    + "' (does not contain '" + exceptionContains + "')",
                    x.getMessage().contains(exceptionContains));
        }
    }
