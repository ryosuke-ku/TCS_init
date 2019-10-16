    public Map<TopicPartition, Long> beginningOffsets(Collection<TopicPartition> partitions) {
        Map<TopicPartition, Long> result = new HashMap<>();
        for (TopicPartition tp : partitions) {
            Long beginningOffset = beginningOffsets.get(tp);
            if (beginningOffset == null)
                throw new IllegalStateException("The partition " + tp + " does not have a beginning offset.");
            result.put(tp, beginningOffset);
        }
        return result;
    }
