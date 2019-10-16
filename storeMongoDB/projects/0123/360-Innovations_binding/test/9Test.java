	public void testPush_DuringInTransaction()
	{
		manager.beginTransaction();
		manager.push(undoRedoValueModelOperation);
		assertTrue("No operation must be contained in operations", manager.getOperations().isEmpty());
		assertTrue("No operation must be contained in redoableOperations", manager.getRedoableOperations().isEmpty());
	}
