	void compareToSelf(FactorGraph fg)
	{
		FactorGraphDiffs fgvsfgName
			= fg.getFactorGraphDiffsByName(fg);
		
		if(!fgvsfgName.noDiffs())
		{
			System.out.println(fgvsfgName);
		}

		assertTrue(fgvsfgName.noDiffs());

		FactorGraphDiffs fgvsfgNameRoot
			= fg.getFactorGraphDiffsByName(fg.copyRoot());
	
		if(!fgvsfgNameRoot.noDiffs())
		{
			System.out.println(fgvsfgNameRoot);
		}

		assertTrue(fgvsfgName.noDiffs());
		
		
		FactorGraphDiffs fgvsfgUUID
			= fg.getFactorGraphDiffsByUUID(fg);
		
		fgvsfgUUID.toString();
		if(!fgvsfgUUID.noDiffs())
		{
			System.out.println(fgvsfgUUID);
		}
		
		assertTrue(fgvsfgUUID.noDiffs());
	}
