	public void test_selectionEventFactoryProperty() {
		final SelectionEventFactory selectionEventFactory = mock(SelectionEventFactory.class);
		listGrid.setSelectionEventFactory(selectionEventFactory);
		assertEquals(selectionEventFactory, listGrid.getSelectionEventFactory());
	}
