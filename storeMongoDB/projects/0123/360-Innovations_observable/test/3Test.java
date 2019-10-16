	public void test_firesEventWhenNonMatchingElementSetOnMatchingElement()
	{
		observableList.set(1, 3);
		eventSpy.assertEvent(subList, ImmutableList.of(), ImmutableList.of(9), 0);
	}
