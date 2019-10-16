    public void testEmit_KeyId�ʎw��_NoRecord()
    {
        // ����
        this.target.open(this.mockConfMap, this.mockContext, this.mockCollector);
        this.target.setRecordHistory(false);

        StreamMessage message = new StreamMessage();
        message.addField("Param1", "Param1");

        // ���{
        this.target.emit(message, "MessageKey", "MessageId");

        // ����
        ArgumentCaptor<List> tupleArgument = ArgumentCaptor.forClass(List.class);
        Mockito.verify(this.mockCollector).emit(tupleArgument.capture(), eq("MessageId"));

        List<Object> argList = tupleArgument.getValue();

        assertThat(argList.size(), equalTo(2));
        assertThat(argList.get(0).toString(), equalTo(""));
        assertThat(argList.get(1), instanceOf(StreamMessage.class));
        StreamMessage sendMessage = (StreamMessage) argList.get(1);
        assertThat(sendMessage.getHeader().getHistory(), nullValue());
        assertThat((StreamMessage) argList.get(1), sameInstance(message));
    }
