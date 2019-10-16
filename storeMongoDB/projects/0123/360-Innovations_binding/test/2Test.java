	public void testUndo_lastOperationIsSavePoint()
	{
		LinkedList<Object> operations = Lists.newLinkedList();
		operations.add(undoRedoValueModelOperation);
		doReturn(operations).when(manager).getOperations();

		doReturn(true).when(manager).checkIfOperationIsSavePoint(undoRedoValueModelOperation);
		manager.undo();

		verify(undoRedoValueModelOperation, never()).undo();

		assertEquals("1 operation must be contained in operations", 1, manager.getOperations().size());
		assertTrue("redoableOperations must now be empty", manager.getRedoableOperations().isEmpty());
	}
