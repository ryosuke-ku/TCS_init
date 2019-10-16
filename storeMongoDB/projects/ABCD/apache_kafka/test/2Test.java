    public void testDisconnectInJoin() {
        subscriptions.subscribe(singleton(topicName), rebalanceListener);

        client.prepareResponse(groupCoordinatorResponse(node, Errors.NONE.code()));
        coordinator.ensureCoordinatorReady();

        // disconnected from original coordinator will cause re-discover and join again
        client.prepareResponse(joinGroupFollowerResponse(1, "consumer", "leader", Errors.NONE.code()), true);
        client.prepareResponse(groupCoordinatorResponse(node, Errors.NONE.code()));
        client.prepareResponse(joinGroupFollowerResponse(1, "consumer", "leader", Errors.NONE.code()));
        client.prepareResponse(syncGroupResponse(singletonList(tp), Errors.NONE.code()));
        coordinator.joinGroupIfNeeded();

        assertFalse(coordinator.needRejoin());
        assertEquals(singleton(tp), subscriptions.assignedPartitions());
        assertEquals(1, rebalanceListener.revokedCount);
        assertEquals(1, rebalanceListener.assignedCount);
        assertEquals(singleton(tp), rebalanceListener.assigned);
    }
