	public List<LeaderData> leadersIn(String leaderboardName, int currentPage, boolean useZeroIndexForRank, int pageSize) {
		if (currentPage < 1) {
			currentPage = 1;
		}

		if (pageSize < 1) {
			pageSize = DEFAULT_PAGE_SIZE;
		}

		if (currentPage > totalPagesIn(leaderboardName, pageSize)) {
			currentPage = totalPagesIn(leaderboardName, pageSize);
		}

		int indexForRedis = currentPage - 1;
		int startingOffset = indexForRedis * pageSize;
		if (startingOffset < 0) {
			startingOffset = 0;
		}
		int endingOffset = (startingOffset + pageSize) - 1;

		Set<Tuple> rawLeaderData = _jedis.zrevrangeWithScores(leaderboardName, startingOffset, endingOffset);
		return massageLeaderData(leaderboardName, rawLeaderData, useZeroIndexForRank);
	}
