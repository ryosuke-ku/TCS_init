    public void testCommitOffsetMetadata() {
        subscriptions.assignFromUser(singleton(tp));

        client.prepareResponse(groupCoordinatorResponse(node, Errors.NONE.code()));
        coordinator.ensureCoordinatorReady();

        client.prepareResponse(offsetCommitResponse(Collections.singletonMap(tp, Errors.NONE.code())));

        AtomicBoolean success = new AtomicBoolean(false);
        coordinator.commitOffsetsAsync(Collections.singletonMap(tp, new OffsetAndMetadata(100L, "hello")), callback(success));
        coordinator.invokeCompletedOffsetCommitCallbacks();
        assertTrue(success.get());

        assertEquals(100L, subscriptions.committed(tp).offset());
        assertEquals("hello", subscriptions.committed(tp).metadata());
    }
