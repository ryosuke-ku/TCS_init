    public void commitOffsetsSync(Map<TopicPartition, OffsetAndMetadata> offsets) {
        invokeCompletedOffsetCommitCallbacks();

        if (offsets.isEmpty())
            return;

        while (true) {
            ensureCoordinatorReady();

            RequestFuture<Void> future = sendOffsetCommitRequest(offsets);
            client.poll(future);

            if (future.succeeded()) {
                if (interceptors != null)
                    interceptors.onCommit(offsets);
                return;
            }

            if (!future.isRetriable())
                throw future.exception();

            time.sleep(retryBackoffMs);
        }
    }
