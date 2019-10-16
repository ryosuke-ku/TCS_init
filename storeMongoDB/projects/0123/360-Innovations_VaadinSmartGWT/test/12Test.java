	public void test_setRecordFactoryProperty() {
		final RecordFactory recordFactory = mock(RecordFactory.class);
		listGrid.setRecordFactory(recordFactory);
		assertEquals(recordFactory, listGrid.getRecordFactory());
	}
