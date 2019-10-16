	static public FactorGraphDiffs	getFactorGraphDiffs(
			   FactorGraph a,
			   FactorGraph b,
			   boolean quickExit,
			   boolean byName)
	{
		return getFactorGraphDiffsNoRootSearch(
				   a.getRootGraph(),
				   b.getRootGraph(),
				   quickExit,
				   byName);
	}
