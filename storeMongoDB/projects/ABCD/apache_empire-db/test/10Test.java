	public void testIsValid()
	{
		assertFalse(StringUtils.isNotEmpty(null));
		assertFalse(StringUtils.isNotEmpty(""));
		assertFalse(StringUtils.isNotEmpty("\t\r\n"));
		assertTrue (StringUtils.isNotEmpty(" test "));
	}
