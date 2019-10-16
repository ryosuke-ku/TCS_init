	public void testTotalPages() {
		rankMembersInLeaderboard(Leaderboard.DEFAULT_PAGE_SIZE + 2);

		assertEquals(2, _leaderboard.totalPages());
	}
