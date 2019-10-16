	private void runTest(TestableMRP test) throws Exception
	{
		boolean ok = false;
		test.trxName = getTrxName();
		try
		{
			test.doIt();
			test.dumpStatus();
			//
			assertEquals(test.name+": MRP Records# not match", test.expectedMRP.size(), test.actualMRP.size());
			for (int i = 0; i < test.expectedMRP.size(); i++)
			{
				assertEquals(test.name+": MRP Record not match",
						test.expectedMRP.get(i), test.actualMRP.get(i));
			}
			//
			assertEquals(test.name+": MRP Notices# not match", test.expectedNotices.size(), test.actualNotices.size());
			for (int i = 0; i < test.expectedNotices.size(); i++)
			{
				assertEquals(test.name+": MRP Record not match",
						test.expectedNotices.get(i), test.actualNotices.get(i));
			}
			//
			ok = true;
		}
		finally
		{
			if (!ok)
			{
				System.err.println("ERRROR_______________________________________");
				test.dumpStatus();
			}
		}
	}
