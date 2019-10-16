    public void updateBeginningOffsets(Map<TopicPartition, Long> newOffsets) {
        beginningOffsets.putAll(newOffsets);
    }
