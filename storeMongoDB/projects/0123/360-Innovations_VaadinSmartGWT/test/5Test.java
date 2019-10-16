	public void test_addSelectionUpdatedHandler() {
		final SelectionUpdatedHandler handler = mock(SelectionUpdatedHandler.class);
		listGrid.addSelectionUpdatedHandler(handler);
		assertTrue(listGrid.getSelectionUpdatedHandlers().contains(handler));
	}
