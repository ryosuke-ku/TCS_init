    public void startSearchNode() throws Exception {
        Settings settings = settingsBuilder()
                .put("gateway.type", "none")
                .put("node.local", "true")
                .put("http.enabled", false)
                .put("index.store.type", "memory")
                .put("index.number_of_shards", "1")
                .put("index.number_of_replicas", "1")
                .build();

        searchNode = nodeBuilder()
                .settings(settings)
                .node();

        searchClient = searchNode.client();

        searchClient.admin()
                .cluster()
                .prepareHealth()
                .setWaitForGreenStatus()
                .execute()
                .actionGet();
    }
