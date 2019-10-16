	public int getItemAmount(Item item) {

		int amount = 0;
		int id = item.getId();

		for (Item i : inventory){
			if (i != null && id == i.getId()){
				amount += i.getAmount();
			}
		}

		return amount;
	}
