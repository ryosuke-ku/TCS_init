	public void test_paintRecordDoubleClickHandlerFlagWhenHandlersAreRegistered() throws PaintException {
		listGrid.addRecordDoubleClickHandler(mock(RecordDoubleClickHandler.class));
		listGrid.paint(paintTarget);
		verify(paintTarget).addAttribute("*hasRecordDoubleClickHandlers", true);
	}
