	public void testUndo_EnsureOperationNotTrackedByUndoRedoManager()
	{
		// Reset since CollectionChange is registered in constructor once and we only want ton ensure no more
		// listeners are added at this point
		reset(collectionValueModel);

		ObservableList<Object> newObservableArrayList = ObservableCollections.newObservableArrayList(newObject);
		when(collectionValueModel.getValue()).thenReturn(newObservableArrayList);
		undoRedoCollectionValueModel.undo(new CollectionChangeEvent(newObservableArrayList, difference));

		InOrder inOrder = inOrder(collectionValueModel, undoRedoManager, collectionValueModel);
		inOrder.verify(collectionValueModel).removeCollectionChangeListener(undoRedoCollectionValueModel.getUndoRedoManagerPushHandler());
		inOrder.verify(undoRedoManager, never()).push(any(CollectionChangeOperation.class));
		inOrder.verify(collectionValueModel).addCollectionChangeListener(undoRedoCollectionValueModel.getUndoRedoManagerPushHandler());

		verify(collectionValueModel).unapply(any(CollectionDifference.class));
	}
