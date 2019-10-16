	public void setCapacity(int capacity) {
		Item[] tmpInventory = new Item[capacity];

		for (int i = 0; i < capacity; i++) {
			tmpInventory[i] = inventory[i];
		}

		// TODO : Throw all other items. What happens to non-falling items in such slots?

		inventory = tmpInventory;
	}
