    public void testRankedInListNoSuchMember() {
   		rankMembersInLeaderboard(Leaderboard.DEFAULT_PAGE_SIZE);

   		assertEquals(Leaderboard.DEFAULT_PAGE_SIZE, _leaderboard.totalMembers());

   		List<String> members = new ArrayList<String>();
   		members.add("member_1");
   		members.add("member_5");
        members.add("member_10");
        members.add("member_" + (Leaderboard.DEFAULT_PAGE_SIZE+1));

   		List<LeaderData> rankedMembers = _leaderboard.rankedInList(members, false);
   		assertEquals(3, rankedMembers.size());

   		assertEquals(25, rankedMembers.get(0).getRank());
   		assertEquals(1.0, rankedMembers.get(0).getScore());

   		assertEquals(21, rankedMembers.get(1).getRank());
   		assertEquals(5.0, rankedMembers.get(1).getScore());

   		assertEquals(16, rankedMembers.get(2).getRank());
   		assertEquals(10.0, rankedMembers.get(2).getScore());
   	}
