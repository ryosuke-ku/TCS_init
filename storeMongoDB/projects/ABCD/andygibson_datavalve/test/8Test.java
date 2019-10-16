	public void testRetain() {
		List<Element> c = new ArrayList<Element>();
		c.add(new Element(15, generateText(15)));
		list.retainAll(c);
		assertEquals(1, list.size());
		assertEquals(15, list.get(0).id);
	}
