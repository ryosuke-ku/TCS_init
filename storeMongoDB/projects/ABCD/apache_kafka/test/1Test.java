    public void testNormalHeartbeat() {
        client.prepareResponse(groupCoordinatorResponse(node, Errors.NONE.code()));
        coordinator.ensureCoordinatorReady();

        // normal heartbeat
        time.sleep(sessionTimeoutMs);
        RequestFuture<Void> future = coordinator.sendHeartbeatRequest(); // should send out the heartbeat
        assertEquals(1, consumerClient.pendingRequestCount());
        assertFalse(future.isDone());

        client.prepareResponse(heartbeatResponse(Errors.NONE.code()));
        consumerClient.poll(0);

        assertTrue(future.isDone());
        assertTrue(future.succeeded());
    }
