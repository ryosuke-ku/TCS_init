	public void testAddAll() {
		List<Element> c = new ArrayList<Element>();
		c.add(new Element(150, generateText(150)));
		list.addAll(c);
		assertEquals(101, list.size());
		assertEquals(150, list.get(100).id);
	}
