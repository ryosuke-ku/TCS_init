	public void testExecuteBatchAction()
	{
		ClearAndAddAllBatchAction action = new ClearAndAddAllBatchAction(Arrays.asList(new Object()));
		undoRedoCollectionValueModel.executeBatchAction(action);
		verify(collectionValueModel).executeBatchAction(action);
	}
