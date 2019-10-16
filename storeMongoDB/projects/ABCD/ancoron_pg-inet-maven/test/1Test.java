    private void assertSplit(String network, int count, String[] res) {
        IPNetwork net = network != null ? new IPNetwork(network) :null;
        
        IPNetwork[] splitted = NetworkUtils.splitByCount(net, count);
        assertSplitResult(res, splitted, count);
    }
