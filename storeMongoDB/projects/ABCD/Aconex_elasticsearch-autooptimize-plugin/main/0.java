    public void optimize() {
        try {
            OptimizeResponse optimizeResponse = indicesAdminClient.prepareOptimize(indexName).setMaxNumSegments(maxNumSegments).setWaitForMerge(true).execute().get();
            log.info("Optimize of '{}' completed: {} success, {} failures", indexName, optimizeResponse.successfulShards(), optimizeResponse.failedShards());
        } catch (Exception e) {
            log.error("Error during ...optimize", e);
        }
    }
