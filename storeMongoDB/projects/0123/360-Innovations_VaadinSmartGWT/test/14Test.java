	public void test_paintSelectionUpdatedHandlerFlagWhenHandlersRegistered() throws PaintException {
		final JsonPaintTarget target = mock(JsonPaintTarget.class);
		listGrid.addSelectionUpdatedHandler(mock(SelectionUpdatedHandler.class));

		listGrid.paintContent(target);
		verify(target).addAttribute("*hasSelectionUpdatedHandlers", true);
	}
