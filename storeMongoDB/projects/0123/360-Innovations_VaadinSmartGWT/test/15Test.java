	public void test_firesRecordDoubleClickEventWhenReceivingClientSideNotification() {
		final RecordDoubleClickHandler handler = mock(RecordDoubleClickHandler.class);
		final HashMap<String, Object> variables = Maps.newHashMap();
		final ListGridField expectedField = new ListGridField();
		final Record expectedRecord = new Record();

		variables.put("onRecordDoubleClick", true);
		variables.put("onRecordDoubleClick.event.record", "{ }");
		variables.put("onRecordDoubleClick.event.recordNum", 0);
		variables.put("onRecordDoubleClick.event.field", expectedField);
		variables.put("onRecordDoubleClick.event.fieldNum", 0);

		final RecordFactory recordFactory = mock(RecordFactory.class);
		when(recordFactory.newRecord(isA(JsonNode.class))).thenReturn(expectedRecord);
		listGrid.setRecordFactory(recordFactory);

		listGrid.addRecordDoubleClickHandler(handler);
		listGrid.changeVariables(null, variables);

		final ArgumentCaptor<RecordDoubleClickEvent> eventCaptor = ArgumentCaptor.forClass(RecordDoubleClickEvent.class);
		verify(handler).onRecordDoubleClick(eventCaptor.capture());
		assertEquals(listGrid, eventCaptor.getValue().getSource());
		assertEquals(listGrid, eventCaptor.getValue().getViewer());
		assertEquals(expectedRecord, eventCaptor.getValue().getRecord());
		assertEquals(0, eventCaptor.getValue().getRecordNum());
		assertEquals(expectedField, eventCaptor.getValue().getField());
		assertEquals(0, eventCaptor.getValue().getFieldNum());
	}
