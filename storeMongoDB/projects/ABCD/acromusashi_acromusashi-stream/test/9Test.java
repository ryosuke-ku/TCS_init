    public void testEmit_KeyId���w��_GroupingStream()
    {
        // ����
        this.target.open(this.mockConfMap, this.mockContext, this.mockCollector);

        StreamMessage message = new StreamMessage();
        message.addField("Param1", "Param1");

        // ���{
        this.target.emitWithNoKeyIdAndGroupingStream(message, "GroupingKey", "StreamId");

        // ����
        ArgumentCaptor<List> argument = ArgumentCaptor.forClass(List.class);
        Mockito.verify(this.mockCollector).emit(eq("StreamId"), argument.capture());

        List<Object> argList = argument.getValue();

        assertThat(argList.size(), equalTo(2));

        assertThat(argList.get(0).toString(), equalTo("GroupingKey"));
        assertThat(argList.get(1), instanceOf(StreamMessage.class));
        StreamMessage sendMessage = (StreamMessage) argList.get(1);
        assertThat(sendMessage.getHeader().getHistory(), nullValue());
        assertThat((StreamMessage) argList.get(1), sameInstance(message));
    }
