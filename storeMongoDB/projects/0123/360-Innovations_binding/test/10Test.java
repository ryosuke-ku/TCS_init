	public void testPush_DuringTransaction_EnsureClearRedoableOperations()
	{
		LinkedList<Object> operations = Lists.newLinkedList();
		operations.add(undoRedoValueModelOperation);
		doReturn(operations).when(manager).getRedoableOperations();

		manager.beginTransaction();
		manager.push(undoRedoValueModelOperation);
		assertEquals("RedoableOperations must not be cleared", 1, operations.size());
	}
