	public void testToArray_Overload()
	{
		Object[] targetArrayType = new Object[] {};
		undoRedoCollectionValueModel.toArray(targetArrayType);
		verify(collectionValueModel).toArray(targetArrayType);
	}
