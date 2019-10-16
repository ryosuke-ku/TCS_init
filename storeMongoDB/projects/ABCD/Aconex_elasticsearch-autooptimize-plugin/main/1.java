    public final void clusterChanged(ClusterChangedEvent event) {

        boolean newLocalNodeMasterState = event.state().nodes().localNodeMaster();

        if (newLocalNodeMasterState != localNodeMaster) {
            localNodeMaster = newLocalNodeMasterState;

            if (newLocalNodeMasterState) {
                log.info("Local node become master");
                serviceIsNowOnMasterNode();
            } else {
                log.info("Local node is now no longer master");
                serviceIsNoLongerOnMasterNode();
            }
        }
    }
