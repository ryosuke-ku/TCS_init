	public void testScoreFor() {
        Double expectedScore = (double) 76;
		_leaderboard.rankMember("member", expectedScore);
		assertEquals(expectedScore, _leaderboard.scoreFor("member"));
	}
