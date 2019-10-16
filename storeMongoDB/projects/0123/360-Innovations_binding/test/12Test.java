	public void testCommit_NullSavePoint_SavePointsNotEmpty()
	{
		SavePoint validSavePoint = new DefaultSavePoint(undoRedoValueModelOperation);
		LinkedList<Object> savePoints = Lists.newLinkedList();
		savePoints.add(validSavePoint);

		doReturn(savePoints).when(manager).getSavePoints();
		manager.commit(null);
	}
