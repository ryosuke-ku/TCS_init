	public void testUncoveredSquareForUncover() throws Exception {
		this.square.setStatus(Square.STATUS.UNCOVERED);
		this.square.uncover();
		assertEquals(Square.STATUS.UNCOVERED, this.square.getStatus());
	}
