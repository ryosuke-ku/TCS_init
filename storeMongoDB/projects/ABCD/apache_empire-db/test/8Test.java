	public void testArrayToString()
	{
		assertEquals(null, StringUtils.arrayToString(null , null));
		assertEquals(null, StringUtils.arrayToString(null , "/"));
		assertEquals(null, StringUtils.arrayToString(new String[]{} , ""));
		assertEquals("test", StringUtils.arrayToString(new String[]{"test"} , "|"));
		assertEquals("12312.3", StringUtils.arrayToString(new Number[]{Integer.valueOf("123"), Double.valueOf("12.3")} , ""));
		assertEquals("firstsecondthird", StringUtils.arrayToString(new String[]{"first", "second", "third"} , null));
		assertEquals(" first \t second \t third ", StringUtils.arrayToString(new String[]{" first ", " second ", " third "} , "\t"));
		assertEquals("null/null", StringUtils.arrayToString(new String[]{null, null} , "/"));
		assertEquals("null", StringUtils.arrayToString(new String[]{null} , "/"));
	}
