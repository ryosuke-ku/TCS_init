	public void testRemove() {
		list.remove(50);
		assertEquals(99, list.size());
		assertEquals(51, list.get(50).id);

	}
