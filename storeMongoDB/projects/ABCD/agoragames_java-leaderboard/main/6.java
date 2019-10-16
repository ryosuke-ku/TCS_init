	public long totalMembersIn(String leaderboardName) {
		return _jedis.zcard(leaderboardName);
	}
