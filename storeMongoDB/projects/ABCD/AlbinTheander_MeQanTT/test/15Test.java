	public void readWorks() throws IOException {
		ConnectMessage msg = new ConnectMessage("test", true, 10000);
		msg.setCredentials("username", "password");
		msg.setWill("/will/topic", "this is will message", QoS.EXACTLY_ONCE,
				true);
		byte[] data = msg.toBytes();
		MessageInputStream is = new MessageInputStream(
				new ByteArrayInputStream(data));
		ConnectMessage message = null;
		try {
			message = (ConnectMessage) is.readMessage();
		} catch (Exception e) {
			e.printStackTrace();
			assertEquals(true, false);
		}
		assertEquals("MQIsdp", message.getProtocolId());
		assertEquals(3, message.getProtocolVersion());
		assertEquals(true, message.hasUsername());
		assertEquals(true, message.hasPassword());
		assertEquals(true, message.isWillRetained());
		assertEquals(QoS.EXACTLY_ONCE, message.getWillQoS());
		assertEquals(true, message.hasWill());
		assertEquals(true, message.isCleanSession());
		assertEquals(10000, message.getKeepAlive());
		assertEquals("test", message.getClientId());
		assertEquals("/will/topic", message.getWillTopic());
		assertEquals("this is will message", message.getWill());
		assertEquals("username", message.getUsername());
		assertEquals("password", message.getPassword());
	}
