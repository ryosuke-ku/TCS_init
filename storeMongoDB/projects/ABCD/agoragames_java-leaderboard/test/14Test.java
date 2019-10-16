	public void testRemoveMembersInScoreRange() {
		rankMembersInLeaderboard(5);

		assertEquals(5, _leaderboard.totalMembers());

		_leaderboard.rankMember("cheater_1", 100);
		_leaderboard.rankMember("cheater_2", 101);
		_leaderboard.rankMember("cheater_3", 102);

		assertEquals(8, _leaderboard.totalMembers());

	    _leaderboard.removeMembersInScoreRange(100, 102);
		assertEquals(5, _leaderboard.totalMembers());
	}
