	public void testValidate()
	{
		assertEquals(null, StringUtils.validate(null));
		assertEquals(null, StringUtils.validate(""));
		assertEquals(null, StringUtils.validate(" \r\n\t "));
		assertEquals("azerty\r\n\tazerty", StringUtils.validate(" \r azerty\r\n\tazerty\t "));
	}
