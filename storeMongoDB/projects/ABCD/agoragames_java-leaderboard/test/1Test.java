	public void testLeaderDataMethods() {
		LeaderData leader = new LeaderData("member", 1, 2);
		
		assertEquals("member", leader.getMember());
		assertEquals(1.0, leader.getScore());
		assertEquals(2, leader.getRank());
		
		leader.setMember("new_member");
		assertEquals("new_member", leader.getMember());
		
		leader.setScore(2);
		assertEquals(2.0, leader.getScore());

		leader.setRank(5);
		assertEquals(5, leader.getRank());
	}
