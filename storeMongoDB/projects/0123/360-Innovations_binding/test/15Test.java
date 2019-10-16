	public void testRemoveAll()
	{
		List removed = Arrays.asList(new Object(), new Object());
		undoRedoCollectionValueModel.removeAll(removed);
		verify(collectionValueModel).removeAll(removed);
	}
