	public void testLRange() {
		j.lpush("test", "a");
		j.lpush("test", "b");
		j.lpush("test", "c");
		j.lpush("test", "d");

		assertEquals(Arrays.asList("a", "b"), j.lrange("test", 0, 1));
		assertEquals(Arrays.asList("c", "d"), j.lrange("test", 2, 5));
		assertEquals(Arrays.asList("c", "d"), j.lrange("test", -2, -1));
		assertEquals(Arrays.asList("c"), j.lrange("test", -2, -2));
		assertEquals(0, j.lrange("test", -7, -6).size());
		assertEquals(0, j.lrange("test", 6, 7).size());
	}
