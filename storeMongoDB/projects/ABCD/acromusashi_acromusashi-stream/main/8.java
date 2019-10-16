    protected void emitWithNoKeyIdAndStream(StreamMessage message, String streamId)
    {
        this.getCollector().emit(streamId, new Values("", message));
    }
