	public void testHincrBy() {
		j.hincrBy("test1", "name", 10);
		assertEquals("10", j.hget("test1", "name"));

		j.hincrBy("test1", "name", -2);
		assertEquals("8", j.hget("test1", "name"));

		j.hset("test1", "name", "5");
		j.hincrBy("test1", "name", 2);
		assertEquals("7", j.hget("test1", "name"));

		j.hincrByFloat("test1", "name", -0.5D);
		assertEquals("6.5", j.hget("test1", "name"));
	}
