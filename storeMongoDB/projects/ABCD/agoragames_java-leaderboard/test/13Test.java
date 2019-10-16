	public void testRankFor() {
        rankMembersInLeaderboard(5);

        assertEquals(2, (long)_leaderboard.rankFor("member_4", false));
        assertEquals(1, (long)_leaderboard.rankFor("member_4", true));
    }
