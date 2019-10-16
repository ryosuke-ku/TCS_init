	public void testNonMineCoveredSquareForUncover() throws Exception {
		this.square.setMine(false);
		this.square.setStatus(Square.STATUS.COVERED);
		this.square.uncover();
	}
