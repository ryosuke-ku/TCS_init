    public void testEmit_Keyw’è_Stream()
    {
        // €”õ
        this.target.open(this.mockConfMap, this.mockContext, this.mockCollector);

        StreamMessage message = new StreamMessage();
        message.addField("Param1", "Param1");

        // À{
        this.target.emitWithOnlyKeyAndStream(message, "MessageKey", "StreamId");

        // ŒŸØ
        ArgumentCaptor<List> argument = ArgumentCaptor.forClass(List.class);
        Mockito.verify(this.mockCollector).emit(eq("StreamId"), argument.capture());

        List<Object> argList = argument.getValue();

        assertThat(argList.size(), equalTo(2));

        assertThat(argList.get(0).toString(), equalTo(""));
        assertThat(argList.get(1), instanceOf(StreamMessage.class));
        StreamMessage sendMessage = (StreamMessage) argList.get(1);
        assertThat(sendMessage.getHeader().getHistory().toString(),
                equalTo("KeyHistory=[MessageKey]"));
        assertThat((StreamMessage) argList.get(1), sameInstance(message));
    }
