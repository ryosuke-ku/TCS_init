    public void stopSearchNode() throws Exception {
        // Reset the index
        ((InternalNode) searchNode).injector().getInstance(Gateway.class).reset();

        searchClient.close();
        searchNode.stop();
    }
