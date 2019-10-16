	public void testAroundMe() {
		rankMembersInLeaderboard(Leaderboard.DEFAULT_PAGE_SIZE * 3 + 1);

		assertEquals(Leaderboard.DEFAULT_PAGE_SIZE * 3 + 1, _leaderboard.totalMembers());

		List<LeaderData> leadersAroundMe = _leaderboard.aroundMe("member_30", false);
		assertEquals(_leaderboard.getPageSize() / 2, leadersAroundMe.size() / 2);

		leadersAroundMe = _leaderboard.aroundMe("member_1", false);
		assertEquals(_leaderboard.getPageSize() / 2 + 1, leadersAroundMe.size());

		leadersAroundMe = _leaderboard.aroundMe("member_76", false);
		assertEquals(_leaderboard.getPageSize() / 2, leadersAroundMe.size() / 2);
	}
