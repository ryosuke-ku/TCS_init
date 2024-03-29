	public void testOverride() {
	    StringProperty PROPERTY1_DESCRIPTOR = new StringProperty("property1", "Test property", null, 0f);
		MockRule rule = new MockRule();
		rule.definePropertyDescriptor(PROPERTY1_DESCRIPTOR);
		rule.setLanguage(LanguageRegistry.getLanguage(Dummy2LanguageModule.NAME));
		rule.setName("name1");
		rule.setProperty(PROPERTY1_DESCRIPTOR, "value1");
		rule.setMessage("message1");
		rule.setDescription("description1");
		rule.addExample("example1");
		rule.setExternalInfoUrl("externalInfoUrl1");
		rule.setPriority(RulePriority.HIGH);

		StringProperty PROPERTY2_DESCRIPTOR = new StringProperty("property2", "Test property", null, 0f);
		RuleReference ruleReference = new RuleReference();
		ruleReference.setRule(rule);
		ruleReference.definePropertyDescriptor(PROPERTY2_DESCRIPTOR);
		ruleReference.setLanguage(LanguageRegistry.getLanguage(DummyLanguageModule.NAME));
		ruleReference.setMinimumLanguageVersion(LanguageRegistry.getLanguage(DummyLanguageModule.NAME).getVersion("1.3"));
		ruleReference.setMaximumLanguageVersion(LanguageRegistry.getLanguage(DummyLanguageModule.NAME).getVersion("1.7"));
		ruleReference.setDeprecated(true);
		ruleReference.setName("name2");
		ruleReference.setProperty(PROPERTY1_DESCRIPTOR, "value2");
		ruleReference.setProperty(PROPERTY2_DESCRIPTOR, "value3");
		ruleReference.setMessage("message2");
		ruleReference.setDescription("description2");
		ruleReference.addExample("example2");
		ruleReference.setExternalInfoUrl("externalInfoUrl2");
		ruleReference.setPriority(RulePriority.MEDIUM_HIGH);

		assertEquals("Override failed", LanguageRegistry.getLanguage(DummyLanguageModule.NAME), ruleReference.getLanguage());
		assertEquals("Override failed", LanguageRegistry.getLanguage(DummyLanguageModule.NAME), ruleReference.getOverriddenLanguage());

		assertEquals("Override failed", LanguageRegistry.getLanguage(DummyLanguageModule.NAME).getVersion("1.3"), ruleReference.getMinimumLanguageVersion());
		assertEquals("Override failed", LanguageRegistry.getLanguage(DummyLanguageModule.NAME).getVersion("1.3"), ruleReference.getOverriddenMinimumLanguageVersion());

		assertEquals("Override failed", LanguageRegistry.getLanguage(DummyLanguageModule.NAME).getVersion("1.7"), ruleReference.getMaximumLanguageVersion());
		assertEquals("Override failed", LanguageRegistry.getLanguage(DummyLanguageModule.NAME).getVersion("1.7"), ruleReference.getOverriddenMaximumLanguageVersion());

		assertEquals("Override failed", false, ruleReference.getRule().isDeprecated());
		assertEquals("Override failed", true, ruleReference.isDeprecated());
		assertEquals("Override failed", true, ruleReference.isOverriddenDeprecated());

		assertEquals("Override failed", "name2", ruleReference.getName());
		assertEquals("Override failed", "name2", ruleReference.getOverriddenName());

		assertEquals("Override failed", "value2", ruleReference.getProperty(PROPERTY1_DESCRIPTOR));
		assertEquals("Override failed", "value3", ruleReference.getProperty(PROPERTY2_DESCRIPTOR));
		assertTrue("Override failed", ruleReference.getPropertyDescriptors().contains(PROPERTY1_DESCRIPTOR));
		assertTrue("Override failed", ruleReference.getPropertyDescriptors().contains(PROPERTY2_DESCRIPTOR));
		assertFalse("Override failed", ruleReference.getOverriddenPropertyDescriptors().contains(PROPERTY1_DESCRIPTOR));
		assertTrue("Override failed", ruleReference.getOverriddenPropertyDescriptors().contains(PROPERTY2_DESCRIPTOR));
		assertTrue("Override failed", ruleReference.getPropertiesByPropertyDescriptor().containsKey(PROPERTY1_DESCRIPTOR));
		assertTrue("Override failed", ruleReference.getPropertiesByPropertyDescriptor().containsKey(PROPERTY2_DESCRIPTOR));
		assertTrue("Override failed", ruleReference.getOverriddenPropertiesByPropertyDescriptor().containsKey(PROPERTY1_DESCRIPTOR));
		assertTrue("Override failed", ruleReference.getOverriddenPropertiesByPropertyDescriptor().containsKey(PROPERTY2_DESCRIPTOR));

		assertEquals("Override failed", "message2", ruleReference.getMessage());
		assertEquals("Override failed", "message2", ruleReference.getOverriddenMessage());

		assertEquals("Override failed", "description2", ruleReference.getDescription());
		assertEquals("Override failed", "description2", ruleReference.getOverriddenDescription());

		assertEquals("Override failed", 2, ruleReference.getExamples().size());
		assertEquals("Override failed", "example1", ruleReference.getExamples().get(0));
		assertEquals("Override failed", "example2", ruleReference.getExamples().get(1));
		assertEquals("Override failed", "example2", ruleReference.getOverriddenExamples().get(0));

		assertEquals("Override failed", "externalInfoUrl2", ruleReference.getExternalInfoUrl());
		assertEquals("Override failed", "externalInfoUrl2", ruleReference.getOverriddenExternalInfoUrl());

		assertEquals("Override failed", RulePriority.MEDIUM_HIGH, ruleReference.getPriority());
		assertEquals("Override failed", RulePriority.MEDIUM_HIGH, ruleReference.getOverriddenPriority());
	}
