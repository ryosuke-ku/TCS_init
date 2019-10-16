	public void testTotalMembersInScoreRange() {
		rankMembersInLeaderboard(5);

		assertEquals(3, _leaderboard.totalMembersInScoreRange(2, 4));
	}
