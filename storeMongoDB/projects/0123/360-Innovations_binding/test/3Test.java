	public void testRedo()
	{
		LinkedList<Object> redoableOperations = Lists.newLinkedList();
		redoableOperations.add(undoRedoValueModelOperation);
		doReturn(redoableOperations).when(manager).getRedoableOperations();

		manager.redo();

		verify(undoRedoValueModelOperation).redo();
		assertEquals("1 operation must be contained in operations", 1, manager.getOperations().size());
		assertTrue("redoableOperations must be empty", manager.getRedoableOperations().isEmpty());

	}
