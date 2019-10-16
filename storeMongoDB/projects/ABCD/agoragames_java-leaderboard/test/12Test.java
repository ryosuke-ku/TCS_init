	public void testCheckMember() {
		rankMembersInLeaderboard(5);

		assertEquals(true, _leaderboard.checkMember("member_1"));
		assertEquals(false, _leaderboard.checkMember("member_8"));
	}
