	public void test_updatesSelectedRecords() {
		final HashMap<String, Object> variables = Maps.<String, Object> newHashMap();
		variables.put("selectedRecords", "[]");

		final ListGridRecordFactory factory = mock(ListGridRecordFactory.class);
		listGrid.setListGridRecordFactory(factory);
		
		final ListGridRecord[] records = new ListGridRecord[0];
		when(factory.newListGridRecords(anyList())).thenReturn(records);

		listGrid.changeVariables(null, variables);
		assertArrayEquals(records, listGrid.getSelectedRecords());
	}
