	private void assertCleaned(String initial, String expected) throws IOException {
        TagNode node = cleaner.clean(initial);
        StringWriter writer = new StringWriter();
        serializer.serialize(node, writer);
        assertEquals(expected, writer.toString());
	}
