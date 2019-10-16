	public void typeIsSetCorrectly() {
			Message msg = new ConcreteMessage(Type.CONNECT);
			assertEquals(1, getTypeValue(msg));
			msg = new ConcreteMessage(Type.CONNACK);
			assertEquals(2, getTypeValue(msg));
			msg = new ConcreteMessage(Type.PUBLISH);
			assertEquals(3, getTypeValue(msg));
			msg = new ConcreteMessage(Type.PUBACK);
			assertEquals(4, getTypeValue(msg));
			msg = new ConcreteMessage(Type.PUBREC);
			assertEquals(5, getTypeValue(msg));
			msg = new ConcreteMessage(Type.PUBREL);
			assertEquals(6, getTypeValue(msg));
			msg = new ConcreteMessage(Type.PUBCOMP);
			assertEquals(7, getTypeValue(msg));
			msg = new ConcreteMessage(Type.SUBSCRIBE);
			assertEquals(8, getTypeValue(msg));
			msg = new ConcreteMessage(Type.SUBACK);
			assertEquals(9, getTypeValue(msg));
			msg = new ConcreteMessage(Type.UNSUBSCRIBE);
			assertEquals(10, getTypeValue(msg));
			msg = new ConcreteMessage(Type.UNSUBACK);
			assertEquals(11, getTypeValue(msg));
			msg = new ConcreteMessage(Type.PINGREQ);
			assertEquals(12, getTypeValue(msg));
			msg = new ConcreteMessage(Type.PINGRESP);
			assertEquals(13, getTypeValue(msg));
			msg = new ConcreteMessage(Type.DISCONNECT);
			assertEquals(14, getTypeValue(msg));
		}
