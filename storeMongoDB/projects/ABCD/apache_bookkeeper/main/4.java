    public void store(final ZooKeeper zk, String ledgersRoot) 
            throws IOException, KeeperException, InterruptedException {
        String ledgersLayout = ledgersRoot + "/"
                + BookKeeperConstants.LAYOUT_ZNODE;
        zk.create(ledgersLayout, serialize(), Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT);
    }
