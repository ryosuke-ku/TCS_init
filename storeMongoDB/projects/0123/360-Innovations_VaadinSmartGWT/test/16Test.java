	public void test_addRecordDoubleClickHandler() {
		final RecordDoubleClickHandler handler = mock(RecordDoubleClickHandler.class);
		final RecordDoubleClickEvent event = newRecordDoubleClickEvent();
		listGrid.addRecordDoubleClickHandler(handler);
		listGrid.fireEvent(event);
		verify(handler).onRecordDoubleClick(event);
	}
