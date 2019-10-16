	public void testRetainAll()
	{
		List retained = Arrays.asList(new Object(), new Object());
		undoRedoCollectionValueModel.retainAll(retained);
		verify(collectionValueModel).retainAll(retained);
	}
