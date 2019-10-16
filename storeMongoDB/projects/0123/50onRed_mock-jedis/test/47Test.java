	public void testHashes() {
		assertEquals(0L, j.hlen("test").longValue());
		assertEquals(0L, j.hdel("test", "name").longValue());
		assertEquals(null, j.hget("test", "name"));
		j.hset("test", "name", "value");
		final Set<String> keys = j.hkeys("test");
		final Map<String, String> entries = j.hgetAll("test");
		final List<String> vals = j.hvals("test");
		assertTrue(keys.contains("name"));
		assertEquals(1, keys.size());
		assertEquals(1, entries.size());
		assertEquals("value", entries.get("name"));
		assertTrue(vals.contains("value"));
		assertEquals(1, vals.size());
		assertTrue(j.hexists("test", "name"));
		assertFalse(j.hexists("test", "name2"));
		assertEquals(1L, j.hlen("test").longValue());
		assertEquals("value", j.hget("test", "name"));
		assertEquals(1L, j.hdel("test", "name").longValue());
	}
