    public void poll(long now) {
        invokeCompletedOffsetCommitCallbacks();

        if (subscriptions.partitionsAutoAssigned() && coordinatorUnknown()) {
            ensureCoordinatorReady();
            now = time.milliseconds();
        }

        if (needRejoin()) {
            // due to a race condition between the initial metadata fetch and the initial rebalance,
            // we need to ensure that the metadata is fresh before joining initially. This ensures
            // that we have matched the pattern against the cluster's topics at least once before joining.
            if (subscriptions.hasPatternSubscription())
                client.ensureFreshMetadata();

            ensureActiveGroup();
            now = time.milliseconds();
        }

        pollHeartbeat(now);
        maybeAutoCommitOffsetsAsync(now);
    }
