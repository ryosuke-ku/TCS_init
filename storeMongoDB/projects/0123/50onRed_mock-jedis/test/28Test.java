	public void testGet() {
		j.set("test", "123");
		assertEquals("123", j.get("test"));
		assertEquals(null, j.get("unknown"));
	}
