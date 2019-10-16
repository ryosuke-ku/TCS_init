	public void testAddItem() {
		// Try to add all the items to the inventory
		final Item[] item = new Item[inventory.getCapacity()];
		for (int i = 0; i< inventory.getCapacity(); i++) {
			// Basic item mock
			item[i] = MockFactory.mockItem(i + 1, 1);

			assertEquals(0, inventory.addItem(item[i]));
		}

		// TODO : Split this test into 3 / 4

		// Try to add an item when inventory is full and item not repeated.
		final Item newItem = mock(Item.class);
		when(newItem.getAmount()).thenReturn(1);
		assertEquals(1, inventory.addItem(newItem));

		// Try to add an item that is repeated when inv. is full
		when(newItem.getId()).thenReturn(2);
		assertEquals(0, inventory.addItem(newItem));
		assertEquals(2, inventory.getItemAmount(newItem));

		// Try to add an item that is repeated when inventory isn't full but item not exceed the limit
		inventory.removeItem(0);

		assertEquals(0, inventory.addItem(newItem));
		assertEquals(3, inventory.getItemAmount(newItem));

		// Try to add an item that is repeated when inventory isnt full and item amount exceeds the limit.
		inventory.removeItem(0);
		inventory.addItem(item[0]);

		when(newItem.getAmount()).thenReturn(9998);
		assertThat(inventory.addItem(newItem), greaterThan(0));
		verify(newItem).addAmount(-9997);
	}
