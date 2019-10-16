	public void testInVisionRange() {
		Position anotherPos = new Position((byte) (X_POSITION +  20), (byte) (Y_POSITION +  20), pos.getMap());

		assertFalse(pos.inVisionRange(anotherPos));

		anotherPos.setX((byte) (X_POSITION + 5));
		anotherPos.setY((byte) (Y_POSITION + 5));

		assertTrue(pos.inVisionRange(anotherPos));

		anotherPos.setMap(2);

		assertFalse(pos.inVisionRange(anotherPos));
	}
