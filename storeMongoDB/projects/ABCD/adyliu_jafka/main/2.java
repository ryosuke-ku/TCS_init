    public void close() {
        if (hasShutdown.compareAndSet(false, true)) {
            closeQuietly(producerPool);
            closeQuietly(brokerPartitionInfo);
        }
    }
