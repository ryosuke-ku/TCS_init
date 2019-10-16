	public void testCleanup() {
		final Item item = MockFactory.mockItem(1, 0);
		final Item item2 = MockFactory.mockItem(2, 1);

		inventory.addItem(item);
		inventory.addItem(item2);

		inventory.cleanup();

		assertEquals(-1, inventory.hasItem(item));
		assertThat(inventory.hasItem(item2), not(equalTo(-1)));
	}
