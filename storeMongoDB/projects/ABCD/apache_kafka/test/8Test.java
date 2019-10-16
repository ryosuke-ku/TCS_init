    public void testIllegalGenerationOnSyncGroup() {
        final String consumerId = "consumer";

        subscriptions.subscribe(singleton(topicName), rebalanceListener);

        client.prepareResponse(groupCoordinatorResponse(node, Errors.NONE.code()));
        coordinator.ensureCoordinatorReady();

        // join initially, but let coordinator rebalance on sync
        client.prepareResponse(joinGroupFollowerResponse(1, consumerId, "leader", Errors.NONE.code()));
        client.prepareResponse(syncGroupResponse(Collections.<TopicPartition>emptyList(), Errors.ILLEGAL_GENERATION.code()));

        // then let the full join/sync finish successfully
        client.prepareResponse(new MockClient.RequestMatcher() {
            @Override
            public boolean matches(ClientRequest request) {
                JoinGroupRequest joinRequest = new JoinGroupRequest(request.request().body());
                return joinRequest.memberId().equals(JoinGroupRequest.UNKNOWN_MEMBER_ID);
            }
        }, joinGroupFollowerResponse(2, consumerId, "leader", Errors.NONE.code()));
        client.prepareResponse(syncGroupResponse(singletonList(tp), Errors.NONE.code()));

        coordinator.joinGroupIfNeeded();

        assertFalse(coordinator.needRejoin());
        assertEquals(singleton(tp), subscriptions.assignedPartitions());
    }
