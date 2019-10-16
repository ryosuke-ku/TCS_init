	public void test_MatchingIndexedRemovalInOriginalAffectsFiltered()
	{
		original.remove(original.indexOf(6));
		assertEquals(ImmutableList.of(5, 7), filtered);
	}
