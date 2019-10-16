	public void testMultipleDB() {
		assertEquals(0L, j.dbSize().longValue());
		j.set("test", "test");
		assertEquals(1L, j.dbSize().longValue());
		j.move("test", 5);
		assertEquals(0L, j.dbSize().longValue());
		j.select(5);
		assertEquals(1L, j.dbSize().longValue());
	}
