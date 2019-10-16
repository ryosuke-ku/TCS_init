	public String readMessage() throws JMSException {
		String message = null;
		
		Message msg = jmsTemplate.receive(destination);
		if(msg instanceof TextMessage) {
			message = ((TextMessage) msg).getText();
		}
		
		return message;
	}
