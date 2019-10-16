    public void testExecute() throws Exception {
        EventSendCommand send = new EventSendCommand();
        send.eventAdmin = mock(EventAdmin.class);
        Capture<Event> eventCapture = newCapture();
        send.eventAdmin.sendEvent(capture(eventCapture));
        expectLastCall();

        replay(send.eventAdmin);
        send.topic = "myTopic";
        send.properties = Arrays.asList("a=b");
        send.execute();
        verify(send.eventAdmin);
        
        Event event = eventCapture.getValue();
        assertThat(event.getTopic(), equalTo("myTopic"));
        assertThat(event.getProperty("a"), equalTo("b"));
    }
