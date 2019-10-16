    protected void emitWithOnlyKey(StreamMessage message, Object messageKey)
    {
        if (this.recordHistory)
        {
            message.getHeader().addHistory(messageKey.toString());
        }

        this.getCollector().emit(new Values("", message));
    }
