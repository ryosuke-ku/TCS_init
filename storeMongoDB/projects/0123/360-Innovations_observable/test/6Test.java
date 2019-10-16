	public void test_firesEventWhenPredicateChanged()
	{
		subList.setFilter(alwaysTrue());
		eventSpy.assertEvent(subList, ImmutableList.of(3, 4, 2), ImmutableList.of(), -1);
	}
