	public void test_NonMatchingMassInsertionInOriginalDoesntAffectFiltered()
	{
		original.addAll(ImmutableList.of(1, 2));
		assertEquals(ImmutableList.of(5, 6, 7), filtered);
	}
