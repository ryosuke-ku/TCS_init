	public void testSets() {
		assertFalse(j.sismember("test", "member 1"));

		assertEquals(2L, (long) j.sadd("test", "member 1", "member 2"));
		assertEquals(1L, (long) j.sadd("test", "member 3"));

		// duplicate member 1. should drop
		assertEquals(0L, (long) j.sadd("test", "member 1"));

		assertEquals(3, j.smembers("test").size());

		// should remove member 3
		assertEquals(1L, (long) j.srem("test", "member 3"));

		List<String> sortedMembers = new ArrayList<String>(2);
		sortedMembers.addAll(j.smembers("test"));
		Collections.sort(sortedMembers);

		assertEquals("member 1", sortedMembers.get(0));
		assertEquals("member 2", sortedMembers.get(1));
	}
