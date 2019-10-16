	public void testIterator()
	{
		undoRedoCollectionValueModel.iterator();
		verify(collectionValueModel).iterator();
	}
