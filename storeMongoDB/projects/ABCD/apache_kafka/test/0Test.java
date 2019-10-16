    public void testCommitOffsetRebalanceInProgress() {
        // we cannot retry if a rebalance occurs before the commit completed
        client.prepareResponse(groupCoordinatorResponse(node, Errors.NONE.code()));
        coordinator.ensureCoordinatorReady();

        client.prepareResponse(offsetCommitResponse(Collections.singletonMap(tp, Errors.REBALANCE_IN_PROGRESS.code())));
        coordinator.commitOffsetsSync(Collections.singletonMap(tp, new OffsetAndMetadata(100L, "metadata")));
    }
