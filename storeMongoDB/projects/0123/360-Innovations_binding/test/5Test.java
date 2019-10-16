	public void testEndTransaction()
	{
		manager.beginTransaction();
		manager.beginTransaction();

		manager.endTransaction();
		manager.endTransaction();
		assertFalse("Transaction must be completed", manager.isCurrentlyInTransaction());
	}
