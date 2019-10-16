    protected void emitWithOnlyKeyAndGrouping(StreamMessage message, Object messageKey,
            String groupingKey)
    {
        if (this.recordHistory)
        {
            message.getHeader().addHistory(messageKey.toString());
        }

        this.getCollector().emit(new Values(groupingKey, message));
    }
