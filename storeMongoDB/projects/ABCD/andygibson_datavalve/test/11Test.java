	public void testSetup() {
		assertEquals(100, list.size());
		assertEquals("Value is 76", list.get(76).value);
		assertEquals(1, list.fillCount());
	}
