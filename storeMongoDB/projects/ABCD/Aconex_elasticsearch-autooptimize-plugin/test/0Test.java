    public void shouldWaitForMerge() {
        IndexOptimizer indexOptimizer = new IndexOptimizer(indicesAdminClient, INDEX_NAME);
        indexOptimizer.optimize();

        verify(optimizeRequestBuilder).setWaitForMerge(true);
    }
