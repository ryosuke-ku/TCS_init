	public void testBeginTransaction_NotInTransaction()
	{
		manager.beginTransaction();
		assertTrue("Transaction has just started", manager.isCurrentlyInTransaction());
	}
