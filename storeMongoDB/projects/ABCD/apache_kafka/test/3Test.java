    public void testRefreshOffsetUnknownTopicOrPartition() {
        client.prepareResponse(groupCoordinatorResponse(node, Errors.NONE.code()));
        coordinator.ensureCoordinatorReady();

        subscriptions.assignFromUser(singleton(tp));
        subscriptions.needRefreshCommits();
        client.prepareResponse(offsetFetchResponse(tp, Errors.UNKNOWN_TOPIC_OR_PARTITION.code(), "", 100L));
        coordinator.refreshCommittedOffsetsIfNeeded();
    }
