    protected void emitWithNoKeyIdAndGroupingStream(StreamMessage message, String groupingKey,
            String streamId)
    {
        this.getCollector().emit(streamId, new Values(groupingKey, message));
    }
