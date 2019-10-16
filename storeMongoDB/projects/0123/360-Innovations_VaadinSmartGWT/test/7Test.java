	public void test_updatesSelectedRecordsBeforeFiringEvent() {
		final HashMap<String, Object> variables = Maps.<String, Object> newHashMap();
		variables.put("onSelectionUpdated.event", true);
		variables.put("selectedRecords", "[]");

		final ListGridRecordFactory factory = mock(ListGridRecordFactory.class);
		listGrid.setListGridRecordFactory(factory);

		final ListGridRecord[] records = new ListGridRecord[0];
		when(factory.newListGridRecords(anyList())).thenReturn(records);

		final CaptureSelectedRecords handler = new CaptureSelectedRecords();
		listGrid.addSelectionUpdatedHandler(handler);

		listGrid.changeVariables(null, variables);
		assertArrayEquals(records, handler.getSelectedRecords());
	}
