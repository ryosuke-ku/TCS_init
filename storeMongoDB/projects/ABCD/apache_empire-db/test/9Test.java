	public void testIsEmpty()
	{
		assertTrue(StringUtils.isEmpty(null));
		assertTrue(StringUtils.isEmpty(""));
		assertTrue(StringUtils.isEmpty("\t\r\n"));
		assertFalse(StringUtils.isEmpty(" test "));
	}
