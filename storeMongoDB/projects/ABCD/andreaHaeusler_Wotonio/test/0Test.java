	public void testGetMax() {
		DBInterface db = mock(DBInterface.class);
		when(db.getDrunkOnNDaysAgo(anyInt())).thenReturn(0).thenReturn(5)
				.thenReturn(0).thenReturn(9).thenReturn(0).thenReturn(3)
				.thenReturn(0);

		WeeklyGraphDataProvider provider = new WeeklyGraphDataProvider(db);
		int max = provider.getMax();
		assertEquals(9, max);
	}
