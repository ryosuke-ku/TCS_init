	public void test_canRemoveSelectionChangeEventFromRegistration() {
		final SelectionChangedHandler handler = mock(SelectionChangedHandler.class);
		final HandlerRegistration registration = listGrid.addSelectionChangedHandler(handler);

		registration.removeHandler();
		assertFalse(listGrid.getSelectionChangedHandlers().contains(handler));
	}
