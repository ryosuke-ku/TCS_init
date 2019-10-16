	public void testSetCapacity() {
		final Item item = mock(Item.class);

		inventory.addItem(item);
		inventory.setCapacity(1);

		assertEquals(1, inventory.getCapacity());
		assertEquals(item, inventory.getItem(0));

		// TODO : Test when capacity is trimmed and items are droped
	}
