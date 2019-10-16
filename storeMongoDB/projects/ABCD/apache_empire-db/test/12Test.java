	public void testReplace()
	{
		assertEquals(null, StringUtils.replace(null, null, null));
		assertEquals("", StringUtils.replace("", null, null));
		assertEquals("test null test", StringUtils.replace("test null test", null, ""));
		assertEquals("test  test", StringUtils.replace("test a test", "a", null));
		assertEquals("test test", StringUtils.replace("test test", "", "oops"));
		assertEquals("test test", StringUtils.replaceAll("test test", null, "oops"));
		assertEquals("testoopsoopstest", StringUtils.replace("test  test", " ", "oops"));
		assertEquals("1-two-3", StringUtils.replace("1 2 3", " 2 ", "-two-"));
	}
