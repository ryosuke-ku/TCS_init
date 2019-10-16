	public void test_clearingOriginalAffectsFiltered()
	{
		original.clear();
		assertEquals(ImmutableList.of(), filtered);
	}
