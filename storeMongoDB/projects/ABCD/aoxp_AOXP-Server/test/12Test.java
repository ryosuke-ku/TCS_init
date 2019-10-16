	public void testSetBelongsToFaction() {
		rep.setBelongsToFaction(!BELONGS_TO_FACTION);

		assertEquals(!BELONGS_TO_FACTION, rep.belongsToFaction());
	}
