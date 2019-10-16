	public void testLeadersWithMultiplePages() {
		rankMembersInLeaderboard(Leaderboard.DEFAULT_PAGE_SIZE * 3 + 1);

		assertEquals(Leaderboard.DEFAULT_PAGE_SIZE * 3 + 1, _leaderboard.totalMembers());

		List<LeaderData> leaders = _leaderboard.leadersIn(1, false);
		assertEquals(_leaderboard.getPageSize(), leaders.size());

		leaders = _leaderboard.leadersIn(2, false);
		assertEquals(_leaderboard.getPageSize(), leaders.size());

		leaders = _leaderboard.leadersIn(3, false);
		assertEquals(_leaderboard.getPageSize(), leaders.size());

		leaders = _leaderboard.leadersIn(4, false);
		assertEquals(1, leaders.size());

		leaders = _leaderboard.leadersIn(_leaderboard.getLeaderboardName(), 1, false, 10);
		assertEquals(10, leaders.size());
	}
