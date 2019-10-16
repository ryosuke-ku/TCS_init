    private static void assertRuleSetReferenceId(final boolean expectedExternal, final String expectedRuleSetFileName,
	    final boolean expectedAllRules, final String expectedRuleName, final String expectedToString,
	    final RuleSetReferenceId reference) {
    	
		assertEquals("Wrong external", expectedExternal, reference.isExternal());
		assertEquals("Wrong RuleSet file name", expectedRuleSetFileName, reference.getRuleSetFileName());
		assertEquals("Wrong all Rule reference", expectedAllRules, reference.isAllRules());
		assertEquals("Wrong Rule name", expectedRuleName, reference.getRuleName());
		assertEquals("Wrong toString()", expectedToString, reference.toString());
    }
