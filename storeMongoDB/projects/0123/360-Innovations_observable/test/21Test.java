	public void test_unfilteredIterator()
	{
		filtered.setFilter(null);
		assertEquals(ImmutableList.of(3, 4, 5, 6, 7), ImmutableList.copyOf(filtered.iterator()));
	}
