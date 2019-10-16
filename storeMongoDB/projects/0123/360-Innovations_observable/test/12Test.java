	public void test_PartialMatchingRetentionInOriginalAffectsFiltered()
	{
		original.retainAll(ImmutableList.of(3, 7));
		assertEquals(ImmutableList.of(7), filtered);
	}
