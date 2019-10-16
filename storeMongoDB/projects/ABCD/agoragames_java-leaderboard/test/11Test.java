	public void testChangeScoreFor() {
		_leaderboard.rankMember("member", 5);
		assertEquals((double) 5,  _leaderboard.scoreFor("member"));

		_leaderboard.changeScoreFor("member", 5);
		assertEquals((double) 10, _leaderboard.scoreFor("member"));

		_leaderboard.changeScoreFor("member", -5);
		assertEquals((double) 5, _leaderboard.scoreFor("member"));
	}
