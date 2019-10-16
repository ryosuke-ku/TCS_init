	public void testDispose()
	{
		undoRedoCollectionValueModel.dispose();
		verify(collectionValueModel).removeCollectionChangeListener(any(DelegateCollectionValueModelCollectionChangeListener.class));
	}
