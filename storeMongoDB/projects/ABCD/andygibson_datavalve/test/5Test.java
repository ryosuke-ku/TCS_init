	public void testAdd() {
		Element e = new Element(10000, "Some Text");
		list.add(new Element(10000, "Some Text"));
		Element last = list.get(list.size() - 1);
		assertEquals(last, e);

	}
