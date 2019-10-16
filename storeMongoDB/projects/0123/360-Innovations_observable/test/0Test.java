	public void test_firesEventWhenElementAddedInSourceThatMatchesFilter()
	{
		observableList.add(8);
		eventSpy.assertEvent(subList, ImmutableList.of(8), ImmutableList.of(), -1);
	}
