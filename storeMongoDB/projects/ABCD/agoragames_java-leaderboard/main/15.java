	public Hashtable<String, Object> scoreAndRankFor(String member, boolean useZeroIndexForRank) {
		return scoreAndRankForIn(_leaderboardName, member, useZeroIndexForRank);
	}
