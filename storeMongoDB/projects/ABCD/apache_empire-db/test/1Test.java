	public void testGetAccessorName(){
		CodeGenConfig config = new CodeGenConfig();
		WriterService service = new WriterService(config);
		
		DBTableColumn col = Mockito.mock(DBTableColumn.class);
		Mockito.when(col.getDataType()).thenReturn(DataType.INTEGER);
		Mockito.when(col.getName()).thenReturn("name");
		assertEquals("getName",service.getAccessorName(col));
		
		DBTableColumn col2 = Mockito.mock(DBTableColumn.class);
		Mockito.when(col2.getDataType()).thenReturn(DataType.BOOL);
		Mockito.when(col2.getName()).thenReturn("name");
		assertEquals("isName",service.getAccessorName(col2));
	}
