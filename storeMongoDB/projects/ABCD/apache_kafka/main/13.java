    public void resume(Collection<TopicPartition> partitions) {
        acquire();
        try {
            for (TopicPartition partition: partitions) {
                log.debug("Resuming partition {}", partition);
                subscriptions.resume(partition);
            }
        } finally {
            release();
        }
    }
