	public void testFlaggedSquareForMarkAsMine() {
		this.square.setStatus(Square.STATUS.FLAGGED);
		this.square.markAsMine();
		assertEquals(Square.STATUS.COVERED, this.square.getStatus());
	}
