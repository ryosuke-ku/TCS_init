	public void testRemoveCollectionChangeListener_EnsureNotRegisteredToDelegate()
	{
		undoRedoCollectionValueModel.removeCollectionChangeListener(listener);

		verify(collectionValueModel, never()).removeCollectionChangeListener(listener);
	}
