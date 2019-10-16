    protected void emitWithOnlyKeyAndGroupingStream(StreamMessage message, Object messageKey,
            String groupingKey, String streamId)
    {
        if (this.recordHistory)
        {
            message.getHeader().addHistory(messageKey.toString());
        }

        this.getCollector().emit(streamId, new Values(groupingKey, message));
    }
