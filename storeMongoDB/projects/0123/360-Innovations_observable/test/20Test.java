	public void test_subList()
	{
		final List<Integer> subList = filtered.subList(0, 2);
		
		assertEquals(ImmutableList.of(5, 6), subList);
	}
