	public void testAddToX() {
		pos.addToX(7);

		assertEquals(X_POSITION + 7, pos.getX());

		pos.addToX(-6);

		assertEquals(X_POSITION + 1, pos.getX());
	}
