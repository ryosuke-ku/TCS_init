	public void test_doesntFireEventWhenNonMatchingElementAddedInSource()
	{
		observableList.add(4);
		assertNull(eventSpy.getEvent());
	}
