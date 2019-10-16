	public void testAddAll()
	{
		List added = Arrays.asList(new Object(), new Object());
		undoRedoCollectionValueModel.addAll(added);
		verify(collectionValueModel).addAll(added);
	}
