		public int compare(Person o1, Person o2) {
			int val = o1.getLastName().compareToIgnoreCase(o2.getLastName());
			//if first names match, then try comparing by the first name			
			if (val == 0) {
				val = o1.getFirstName().compareToIgnoreCase(o2.getFirstName());
			}
			return val;
		}
