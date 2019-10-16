	public void testScoreAndRankFor() {
		rankMembersInLeaderboard(5);

		Hashtable<String, Object> data = _leaderboard.scoreAndRankFor("member_1", false);

		assertEquals("member_1", data.get("member"));
		assertEquals(1.0, data.get("score"));
		assertEquals(5, ((Long) data.get("rank")).longValue());
	}
