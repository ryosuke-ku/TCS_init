	public void cleanup() {
		for (int i = inventory.length - 1; i >= 0; i--) {
			if (inventory[i] != null && inventory[i].getAmount() == 0) {
				inventory[i] = null;
			}
		}
	}
