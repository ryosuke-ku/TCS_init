    public void testMultipleFullRuleSet() {
	List<RuleSetReferenceId> references = RuleSetReferenceId
		.parse("rulesets/java/unusedcode.xml,rulesets/java/basic.xml");
	assertEquals(2, references.size());
	assertRuleSetReferenceId(true, "rulesets/java/unusedcode.xml", true, null, "rulesets/java/unusedcode.xml",
		references.get(0));
	assertRuleSetReferenceId(true, "rulesets/java/basic.xml", true, null, "rulesets/java/basic.xml", references
		.get(1));
    }
