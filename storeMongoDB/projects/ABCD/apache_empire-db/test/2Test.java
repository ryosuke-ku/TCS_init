	public void testGetMutatorName(){
		CodeGenConfig config = new CodeGenConfig();
		WriterService service = new WriterService(config);
		
		DBTableColumn col = Mockito.mock(DBTableColumn.class);
		Mockito.when(col.getDataType()).thenReturn(DataType.DECIMAL);
		Mockito.when(col.getName()).thenReturn("name");
		assertEquals("setName", service.getMutatorName(col));		
	}
