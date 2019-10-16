	public void test_valueModel_with_undoSupport()
	{
		UndoSupport undoSupport = mock(UndoSupport.class, withSettings().extraInterfaces(ValueModel.class));
		when(undoSupport.getUndoableValue(eq("test"))).thenReturn(1);
		ValueModel valueModel = (ValueModel) undoSupport;

		UndoRedoManager manager = new UndoRedoManager();
		UndoRedoValueModel undoRedoValueModel = new UndoRedoValueModel(manager, (ValueModel) undoSupport, new StandardChangeSupportFactory());
		undoRedoValueModel.setValue("test");
		manager.undo();
		verify(valueModel).setValue(eq(1));
	}
