	public void testGetValueDaysAgo() {
		DBInterface db = mock(DBInterface.class);
		when(db.getDrunkOnNDaysAgo(anyInt())).thenReturn(1).thenReturn(2)
				.thenReturn(7).thenReturn(9).thenReturn(3).thenReturn(3)
				.thenReturn(4);

		WeeklyGraphDataProvider provider = new WeeklyGraphDataProvider(db);
		int value = provider.getValueDaysAgo(3);
		assertEquals(9, value);
	}
