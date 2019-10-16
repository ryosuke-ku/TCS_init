	public void testCData() throws IOException {
		cleaner.getProperties().setAddNewlineToHeadAndBody(false);
		String start = "<html><head>";
		String end = "</head><body>1</body></html>";
		assertCleaned(start + "<style type=\"text/css\">/*<![CDATA[*/\n#ampmep_188 { }\n/*]]>*/</style>" + end,
				start + "<style type=\"text/css\">/*<![CDATA[*/\n#ampmep_188 { }\n/*]]>*/</style>" + end);
	}
