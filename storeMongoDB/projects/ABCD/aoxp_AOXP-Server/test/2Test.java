	public void testAddToY() {
		pos.addToY(7);

		assertEquals(Y_POSITION + 7, pos.getY());

		pos.addToY(-6);

		assertEquals(Y_POSITION + 1, pos.getY());
	}
