	public void testSort() {
		j.lpush("test", "a");
		j.lpush("test", "c");
		j.lpush("test", "b");
		j.lpush("test", "d");

		try {
			j.sort("test");
			fail("Sorting numbers is default");
		} catch (JedisDataException e) {
		}

		assertEquals(Arrays.asList("a", "b", "c", "d"), j.sort("test", new SortingParams().alpha()));
		assertEquals(Arrays.asList("d", "c", "b", "a"), j.sort("test", new SortingParams().desc().alpha()));

		j.sort("test", new SortingParams().alpha(), "newkey");

		assertEquals(Arrays.asList("a", "b", "c", "d"), j.lrange("newkey", 0, 10));

		j.sadd("settest", "1", "2", "3", "4", "5", "6");

		assertEquals(Arrays.asList("1", "2", "3", "4", "5", "6"), j.sort("settest"));
		assertEquals(Arrays.asList("3", "4", "5"), j.sort("settest", new SortingParams().limit(2, 3)));
		assertEquals(Arrays.asList("4", "3", "2"), j.sort("settest", new SortingParams().limit(2, 3).desc()));
	}
