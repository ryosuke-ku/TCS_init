	public void test_PartialMatchingMassRemovalInFilteredAffectsFullyOriginal()
	{
		final boolean result = filtered.removeAll(ImmutableList.of(3, 7));

		assertTrue(result);
		assertEquals(ImmutableList.of(4, 5, 6), original);
	}
