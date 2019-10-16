	public void testRuleSetReference() {
		RuleReference ruleReference = new RuleReference();
		RuleSetReference ruleSetReference = new RuleSetReference();
		ruleReference.setRuleSetReference(ruleSetReference);
		assertEquals("Not same rule set reference", ruleSetReference, ruleReference.getRuleSetReference());
	}
