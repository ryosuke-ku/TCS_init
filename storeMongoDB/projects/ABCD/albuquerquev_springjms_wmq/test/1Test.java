	public void testReadMessage() throws JMSException {
		String readMessage = messageService.readMessage();
		
		assertEquals(readMessage, message);
	}
