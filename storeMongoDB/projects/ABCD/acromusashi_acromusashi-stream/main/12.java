    protected void emitWithOnlyKeyAndStream(StreamMessage message, Object messageKey,
            String streamId)
    {
        if (this.recordHistory)
        {
            message.getHeader().addHistory(messageKey.toString());
        }

        this.getCollector().emit(streamId, new Values("", message));
    }
