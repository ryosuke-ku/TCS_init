	public void testRollbackMustBeInTransaction()
	{
		final UndoRedoManager undoRedoManager = new UndoRedoManager();
		SavePoint createSavePoint = undoRedoManager.createSavePoint();
		undoRedoManager.push(new UndoRedoOperation()
			{

				@Override
				public void undo()
				{
					undoRedoManager.push(undoRedoValueModelOperation);
				}

				@Override
				public void redo()
				{}
			});
		undoRedoManager.rollback(createSavePoint);

		assertTrue(undoRedoManager.getOperations().isEmpty());
	}
