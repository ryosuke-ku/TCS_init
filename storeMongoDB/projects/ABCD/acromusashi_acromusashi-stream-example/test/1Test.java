    public void testTextTuple_HTTPGet����() throws IOException
    {
        // ����
        String getResult = "{\"contents\":\"Test Message\"}";

        Mockito.doReturn(getResult).when(this.httpClient).execute((HttpUriRequest) anyObject(),
                (ResponseHandler) anyObject());

        // ���{
        this.target.nextTuple();

        // ����
        ArgumentCaptor<List> argument = ArgumentCaptor.forClass(List.class);
        Mockito.verify(this.mockCollector).emit(argument.capture());

        List<Object> argList = argument.getValue();

        assertThat(argList.size(), equalTo(1));
        assertThat(argList.get(0), instanceOf(Message.class));

        Message emitResult = (Message) argList.get(0);
        Header header = emitResult.getHeader();

        assertThat(header.getType(), is("http"));
        assertThat(header.getMessageId(), notNullValue());
        assertThat((header.getTimestamp() > 0), is(true));

        assertThat(emitResult.getBody().toString(), is(getResult));
    }
