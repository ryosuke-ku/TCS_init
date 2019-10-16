    public long position(TopicPartition partition) {
        ensureNotClosed();
        if (!this.subscriptions.isAssigned(partition))
            throw new IllegalArgumentException("You can only check the position for partitions assigned to this consumer.");
        Long offset = this.subscriptions.position(partition);
        if (offset == null) {
            updateFetchPosition(partition);
            offset = this.subscriptions.position(partition);
        }
        return offset;
    }
