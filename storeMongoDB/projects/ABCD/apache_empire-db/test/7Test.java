	public void testNullIfEmpty()
	{
		assertEquals(null, StringUtils.nullIfEmpty(null));
		assertEquals(null, StringUtils.nullIfEmpty(""));
		assertEquals(" ", StringUtils.nullIfEmpty(" "));
		assertEquals("\r\n\t", StringUtils.nullIfEmpty("\r\n\t"));
		assertEquals(" value ", StringUtils.nullIfEmpty(" value "));
	}
