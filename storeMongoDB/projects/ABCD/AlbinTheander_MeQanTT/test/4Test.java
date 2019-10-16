	public void dupFlagIsSetCorrectly() {
		Message msg= new ConcreteMessage(Type.CONNECT);
		byte[] bytes = msg.toBytes();
		assertEquals(0, bytes[0] & 8);
		msg.setDup(true);
		bytes = msg.toBytes();
		assertEquals(8, bytes[0] & 8);
	}
