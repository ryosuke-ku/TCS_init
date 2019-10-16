	public void testNameOrdering() {
		ObjectDataset<Person> ds = buildObjectDataset();
		ds.setOrderKey("name");
		ds.setOrderAscending(false);
		ds.setMaxRows(10);
		//check descending
		String name = null;
		
		for (Person p : ds) {			
			if (name != null) {
				int diff = p.getName().compareToIgnoreCase(name);
				assertTrue("Next Id is not less than the last",diff < 0);
			}
			name = p.getName();
		}
		
		
		//now reverse the ordering
		ds.setOrderAscending(true);
		name = null;
		for (Person p : ds) {
			if (name != null) {
				int diff = p.getName().compareToIgnoreCase(name);
				assertTrue("Next Id is not greater than the last",diff > 0);
			}
			name = p.getName();
		}
		
	}
