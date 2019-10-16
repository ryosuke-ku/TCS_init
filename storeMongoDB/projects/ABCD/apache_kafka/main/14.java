    public Set<TopicPartition> paused() {
        acquire();
        try {
            return Collections.unmodifiableSet(subscriptions.pausedPartitions());
        } finally {
            release();
        }
    }
