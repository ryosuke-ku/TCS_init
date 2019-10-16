	public void test_firesEventWhenElementRemovedInSourceThatMatchesFilter()
	{
		observableList.remove((Integer) 7);
		eventSpy.assertEvent(subList, ImmutableList.of(), ImmutableList.of(7), 3);
	}
