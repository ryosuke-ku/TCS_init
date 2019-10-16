	public void test_MatchingIndexedAddInFilteredAffectsOriginal()
	{
		filtered.add(0, 8);
		assertEquals(ImmutableList.of(3, 4, 8, 5, 6, 7), original);
	}
